package com.example.studyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.Data.DBHlpr
import com.example.studyapp.Data.Details
import com.example.studyapp.RVAdapter.RVAdapterKotlin
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Kotlin : AppCompatActivity() {

    var KotlinList = ArrayList<Details>()

    companion object{
        lateinit var DB: DBHlpr
    }
    var RV  = RVAdapterKotlin(this, KotlinList)
    lateinit var rvKotlin : RecyclerView
    lateinit var fabAddKotlin : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        DB = DBHlpr(applicationContext)

        title = "Kotlin Review"     //Page title

        fabAddKotlin = findViewById(R.id.fabAddKotlin)
        rvKotlin = findViewById(R.id.rvKotlin)

        getDate()
        rvKotlin.adapter = RV
        rvKotlin.layoutManager = LinearLayoutManager(this)

        fabAddKotlin.setOnClickListener {
            AddData()
        }

    }

    fun AddData(){
        val alert =  AlertDialog.Builder(this)
        val L = LinearLayout(this)
        val etTitleAlert = EditText(this)
        etTitleAlert.setHint("Enter Title")
        val etDecAlert = EditText(this)
        etDecAlert.setHint("Enter Description")
        val etDetAlert = EditText(this)
        etDetAlert.setHint("Enter Details")
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
                    val new = Details(0,TitleAlert,DecAlert,DetAlert)
                    DB.AddKotlinData(new)
                    getDate()
                }else{
                    Toast.makeText(this,"Please enter values", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()
    }

    fun getDate(){
        KotlinList.clear()
        KotlinList = DB.getKotlinData()
        RV.Update(KotlinList)
    }

    fun DeleteAlert(SelectTopic: Details){
        val alert =  AlertDialog.Builder(this)
        alert.apply {
            setTitle("Delete")
            setMessage("Are you sure you want delete (${SelectTopic.Title}) ?")
            setPositiveButton("DELETE IT"){
                    dialog,which -> DB.deleteKotlinData(SelectTopic) ; getDate()

            }
            setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            show()
        }
    }

    fun UpdateAlert(SelectTopic: Details){
        val alert =  AlertDialog.Builder(this)
        val L = LinearLayout(this)

        val etTitleAlert = EditText(this)
        etTitleAlert.setText(SelectTopic.Title)     // Title

        val etDecAlert = EditText(this)
        etDecAlert.setText(SelectTopic.Description) // Description

        val etDetAlert = EditText(this)
        etDetAlert.setText(SelectTopic.Details)     // Details

        L.orientation = LinearLayout.VERTICAL
        L.apply {
            addView(etTitleAlert)
            addView(etDecAlert)
            addView(etDetAlert)
        }

        alert.setTitle("Edit The Kotlin Topic")
            .setView(L)
            .setPositiveButton("UPDATE"){
                    dialog,which ->
                if(etTitleAlert.text.isNotEmpty() && etDecAlert.text.isNotEmpty() && etDetAlert.text.isNotEmpty()){
                    val ID = SelectTopic.ID
                    val TitleAlert = etTitleAlert.text.toString()
                    val DecAlert = etDecAlert.text.toString()
                    val DetAlert = etDetAlert.text.toString()
                    val new = Details(ID,TitleAlert,DecAlert,DetAlert)
                    DB.updateKotlinDate(new)
                    getDate()
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