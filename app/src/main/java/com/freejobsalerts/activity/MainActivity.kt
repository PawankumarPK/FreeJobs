
package com.freejobsalerts.activity

import android.os.Bundle
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
}
