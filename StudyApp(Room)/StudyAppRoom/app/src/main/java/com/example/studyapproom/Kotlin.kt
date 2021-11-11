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
import com.example.studyapproom.Data.KotlinDetails
import com.example.studyapproom.RVAdapter.RVAdapterKotlin
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Kotlin : AppCompatActivity() {

    private val myViewKotlin by lazy { ViewModelProvider(this).get(myViewModel::class.java) }
    val RV = RVAdapterKotlin(this)
    lateinit var rvKotlin: RecyclerView
    lateinit var fabAddKotlin: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        title = "Kotlin Review"

        rvKotlin = findViewById(R.id.rvKotlin)
        fabAddKotlin = findViewById(R.id.fabAddKotlin)

        myViewKotlin.getKotlinDetails().observe(this,{
                KotlinList -> RV.Update(KotlinList)
        })

        fabAddKotlin.setOnClickListener {
            AddAlert()
        }

        rvKotlin.adapter = RV
        rvKotlin.layoutManager = LinearLayoutManager(this)

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
                    val new = KotlinDetails(0,TitleAlert,DecAlert,DetAlert)
                    myViewKotlin.addKotlinDetails(new)
                }else{
                    Toast.makeText(this,"Please enter values", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()
    }

    fun DeleteAlert(SelectTopic: KotlinDetails){
        val alert =  AlertDialog.Builder(this)
        alert.apply {
            setTitle("Delete")
            setMessage("Are you sure you want delete (${SelectTopic.Title}) ?")
            setPositiveButton("DELETE IT"){
                    dialog,which -> myViewKotlin.deleteKotlinDetails(SelectTopic)

            }
            setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            show()
        }
    }

    fun UpdateAlert(SelectTopic: KotlinDetails){
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

        alert.setTitle("Edit The Kotlin Topic")
            .setView(L)
            .setPositiveButton("UPDATE"){
                    dialog,which ->
                if(etTitleAlert.text.isNotEmpty() && etDecAlert.text.isNotEmpty() && etDetAlert.text.isNotEmpty()){
                    val ID = SelectTopic.ID
                    val TitleAlert = etTitleAlert.text.toString()
                    val DecAlert = etDecAlert.text.toString()
                    val DetAlert = etDetAlert.text.toString()
                    val newUpdate = KotlinDetails(ID,TitleAlert,DecAlert,DetAlert)
                    myViewKotlin.updateKotlinDetails(newUpdate)
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