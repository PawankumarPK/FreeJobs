package com.freejobsalerts.fragment



import android.os.Bundle
import android.support.v4.app.Fragment
import com.freejobsalerts.activity.MainActivity


open class BaseFragment : Fragment() {

    lateinit var mainActivity: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = activity as MainActivity
    }

}
