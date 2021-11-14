package com.example.imagesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var NController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NController = Navigation.findNavController(this,R.id.fMain)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NController.navigateUp()
    }

}