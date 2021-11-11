package com.example.studyapproom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapproom.Data.AndroidDetails
import com.example.studyapproom.RVAdapter.RVAdapterAndroid
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Android : AppCompatActivity() {

    private val myViewAndroid by lazy { ViewModelProvider(this).get(myViewModel::class.java) }
    val RV = RVAdapterAndroid(this)
    lateinit var rvAndroid: RecyclerView
    lateinit var fabAddAndroid: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)

        title = "Android Review"

        rvAndroid = findViewById(R.id.rvAndroid)
        fabAddAndroid = findViewById(R.id.fabAddAndroid)

        myViewAndroid.getAndroidDetails().observe(this,{
            AndroidList -> RV.Update(AndroidList)
        })

        fabAddAndroid.setOnClickListener {
            AddAlert()
        }

        rvAndroid.adapter = RV
        rvAndroid.layoutManager = LinearLayoutManager(this)

    }

    fun AddAlert(){
        val alert =  AlertDialog.Builder(this)
        val L = LinearLayout(this)
        val etTitleAlert = EditText(this)
        etTitleAlert.setHint("Enter Title")
        val etDecAlert = EditText(this)
        etDecAlert.setHint("Enter Description")
        val etDetAlert = EditText(this)
        etDetAlert.setHint("Enter More Details")
        L.orientation = LinearLayout.VERTICAL
        L.apply {
            addView(etTitleAlert)
            addView(etDecAlert)
            addView(etDetAlert)
        }

        alert.setTitle("Add new Topic")
            .setView(L)
            .setPositiveButton("ADD"){
                    dialog,which ->
                if(etTitleAlert.text.isNotEmpty() && etDecAlert.text.isNotEmpty() && etDetAlert.text.isNotEmpty()){
                    val TitleAlert = etTitleAlert.text.toString()
                    val DecAlert = etDecAlert.text.toString()
                    val DetAlert = etDetAlert.text.toString()
                    val new = AndroidDetails(0,TitleAlert,DecAlert,DetAlert)
                    myViewAndroid.addAndroidDetails(new)
                }else{
                    Toast.makeText(this,"Please enter values", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()
    }

    fun DeleteAlert(SelectTopic: AndroidDetails){
            val alert =  AlertDialog.Builder(this)
        alert.apply {
            setTitle("Delete")
            setMessage("Are you sure you want delete (${SelectTopic.Title}) ?")
            setPositiveButton("DELETE IT"){
                    dialog,which -> myViewAndroid.deleteAndroidDetails(SelectTopic)

            }
            setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            show()
        }
    }

    fun UpdateAlert(SelectTopic: AndroidDetails){
        val alert =  AlertDialog.Builder(this)
        val L = LinearLayout(this)

        val etTitleAlert = EditText(this)
        etTitleAlert.setText(SelectTopic.Title)     // Title

        val etDecAlert = EditText(this)
        etDecAlert.setText(SelectTopic.Description) // Description

        val etDetAlert = EditText(this)
        etDetAlert.setText(SelectTopic.MoreDetails)     // Details

        L.orientation = LinearLayout.VERTICAL
        L.apply {
            addView(etTitleAlert)
            addView(etDecAlert)
            addView(etDetAlert)
        }

        alert.setTitle("Edit The Android Topic")
            .setView(L)
            .setPositiveButton("UPDATE"){
                    dialog,which ->
                if(etTitleAlert.text.isNotEmpty() && etDecAlert.text.isNotEmpty() && etDetAlert.text.isNotEmpty()){
                    val ID = SelectTopic.ID
                    val TitleAlert = etTitleAlert.text.toString()
                    val DecAlert = etDecAlert.text.toString()
                    val DetAlert = etDetAlert.text.toString()
                    val newUpdate = AndroidDetails(ID,TitleAlert,DecAlert,DetAlert)
                    myViewAndroid.updateAndroidDetails(newUpdate)
                }else{
                    Toast.makeText(this,"Please enter values", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()
    }
}