package com.ardaozceviz.wired.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.controllers.UserInterface

class MainActivity : AppCompatActivity() {
    private lateinit var userInterface: UserInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userInterface = UserInterface(this)
    }

    override fun onResume() {
        super.onResume()
        userInterface.initialize()

    }
}
