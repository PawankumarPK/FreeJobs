package com.freejobsalerts.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.freejobsalerts.R
import com.freejobsalerts.fragment.Home

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, Home())
            .addToBackStack(null).commit()
    }

    override fun onBackPressed() {

            super.onBackPressed()



    }

    private fun exitApp(exit: Boolean) {
        AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(resources.getString(R.string.Exit))
            .setMessage(resources.getString(R.string.areYouSure))
            .setPositiveButton(resources.getString(R.string.Yes)) { _, _ ->
                if (exit) finish()
                else super.onBackPressed() }.setNegativeButton(resources.getString(R.string.No),null).show()


    }
}
