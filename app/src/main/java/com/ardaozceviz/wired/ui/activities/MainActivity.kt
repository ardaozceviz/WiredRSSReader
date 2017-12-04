package com.ardaozceviz.wired.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.ui.controller.UserInterface

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userInterface = UserInterface(this)
        userInterface.initialize()
    }

}
