/*
*  Recycler View App
*  Author : @MjdAbdullah
* */

package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var clMain : ConstraintLayout
    lateinit var tvMain : TextView
    lateinit var bEnter : Button
    lateinit var edData : EditText
    lateinit var ArrData : ArrayList<String>
    lateinit var myRV : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRV = findViewById<RecyclerView>(R.id.cvMain)
        bEnter = findViewById<Button>(R.id.bEnter)
        edData = findViewById<EditText>(R.id.edData)
        tvMain = findViewById<TextView>(R.id.tvMain)

        ArrData = ArrayList()
        myRV.adapter = RecyclerViewAdapter(this, ArrData)
        myRV.layoutManager = LinearLayoutManager(this)

        bEnter.setOnClickListener { AddData() }

    }

    fun AddData(){
        var inp = edData.getText().toString()
        if(inp.isEmpty()){
            Snackbar.make(clMain, "There is No Text !!", Snackbar.LENGTH_LONG).show()
        }else{
            ArrData.add(inp)
            myRV.adapter = RecyclerViewAdapter(this, ArrData)
        }

        // Clear the Edit Text field.
        edData.getText().clear()
        edData.clearFocus()
    }
}