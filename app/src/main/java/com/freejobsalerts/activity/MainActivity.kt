package com.freejobsalerts.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import com.freejobsalerts.R
import com.freejobsalerts.fragment.Home
import kotlinx.android.synthetic.main.dialog_logout.*

class MainActivity : AppCompatActivity() {

    private val displayRectangle = Rect()
    private var width = 0
    private lateinit var dialog: Dialog
    private lateinit var metrics: DisplayMetrics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        metrics = DisplayMetrics()
        this.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        width = (displayRectangle.width() * 0.9f).toInt()
        this.windowManager.defaultDisplay.getMetrics(metrics)
        dialog = Dialog(this)
        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, Home()).commit()
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount == 0) {
            logoutDialog()
        } else {
            super.onBackPressed()
        }


    }

    @SuppressLint("InflateParams")
    private fun logoutDialog() {
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_logout, null, false)
        layout.minimumWidth = width
        dialog.setContentView(layout)
        dialog.buttonLogout.setOnClickListener { finish()}
        dialog.mRate.setOnClickListener { ratingListener() }
        dialog.mCancelDialog.setOnClickListener { dialog.dismiss() }
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }


    private fun ratingListener() {
        var uri: Uri
        try {
            uri = Uri.parse("market://details?id=" + this.packageName)
            val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
            startActivity(myAppLinkToMarket)
        } catch (e: ActivityNotFoundException) {
            uri = Uri.parse("https://play.google.com/store/apps/details?id=" + this.packageName)
            val goMarket = Intent(Intent.ACTION_VIEW, uri)
            startActivity(goMarket)
        }

    }

    /*private fun exitApp(exit: Boolean) {
        AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(resources.getString(R.string.Exit))
            .setMessage(resources.getString(R.string.areYouSure))
            .setPositiveButton(resources.getString(R.string.Yes)) { _, _ ->
                if (exit) finish()
                else super.onBackPressed() }.setNegativeButton(resources.getString(R.string.No),null).show()


    }*/

}
