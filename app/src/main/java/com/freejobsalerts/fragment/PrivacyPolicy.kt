package com.freejobsalerts.fragment


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.*
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.freejobsalerts.R
import com.freejobsalerts.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_privacypolicy.*

class PrivacyPolicy : BaseFragment() {

    private val urlSearch = "http://freejobsalert.co/Privacy-Policy.aspx"
    //private var webView: WebView? = null
    private var progressDialog: ProgressDialog? = null

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(message: Message) {
            when (message.what) {
                1 -> {
                    webViewGoBack()
                }
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacypolicy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = activity as MainActivity
        mainActivity.mRelativeLayoutOne.visibility = View.GONE

        webView.setOnKeyListener(object : View.OnKeyListener {

            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.action == MotionEvent.ACTION_UP
                    && webView.canGoBack()
                ) {
                    handler.sendEmptyMessage(1)
                    return true
                }

                return false
            }

        })


        mVisitSiteBackButton.setOnClickListener { mVisitSiteBackButtonFunction() }

        progressDialog = ProgressDialog(context)
        progressDialog!!.max = 100
        progressDialog!!.setMessage("Please Wait")
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog!!.progress = 0
        progressDialog!!.setCancelable(false)

        //loading webview
        webView!!.webViewClient = WebViewClient()
        webView!!.settings.javaScriptEnabled = true
        webView!!.loadUrl(urlSearch)
        webView!!.isHorizontalScrollBarEnabled = true
        val webSettings = webView!!.settings
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.setSupportZoom(true)


        //for Zoom in of webpage
        /* webView!!.settings.setSupportZoom(true)
         webView!!.settings.builtInZoomControls = true
         webView!!.settings.displayZoomControls = true
 */
        //for the progress dialog to appear
        webView!!.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(View: WebView, progress: Int) {

                progressDialog!!.progress = progress
                if (progress == 100) {
                    progressDialog!!.dismiss()
                } else {
                    progressDialog!!.show()
                }
            }
        }

    }

    private fun webViewGoBack() {
        webView.goBack()
    }

    private fun mVisitSiteBackButtonFunction() {
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, Home())
            .addToBackStack(null).commit()
    }
}
