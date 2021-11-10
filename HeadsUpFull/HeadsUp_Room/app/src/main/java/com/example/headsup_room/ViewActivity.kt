package com.example.headsup_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.headsup_room.Data.Celebrity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewActivity : AppCompatActivity() {

    private val myView1 by lazy { ViewModelProvider(this).get(myViewModel::class.java) }

    lateinit var rvCelebrity : RecyclerView
    var RV = RVAdapter(this)

    lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        myView1.getCelebrity().observe(this, {
                celebrity -> RV.Update(celebrity)
        })

        fabAdd = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            AddAlert()
        }
        rvCelebrity =findViewById(R.id.rvCelebrity)
        rvCelebrity.adapter = RV
        rvCelebrity.layoutManager = LinearLayoutManager(this)
    }

    fun AddAlert(){
        val alert =  AlertDialog.Builder(this)
        val L = LinearLayout(this)
        val etNameAlert = EditText(this)
        etNameAlert.setHint("Enter Name")
        val etT1Alert = EditText(this)
        etT1Alert.setHint("Enter Taboo1")
        val etT2Alert = EditText(this)
        etT2Alert.setHint("Enter Taboo2")
        val etT3Alert = EditText(this)
        etT3Alert.setHint("Enter Taboo3")
        L.orientation = LinearLayout.VERTICAL
        L.apply {
            addView(etNameAlert)
            addView(etT1Alert)
            addView(etT2Alert)
            addView(etT3Alert)
        }
        alert.setTitle("Add New Celebrity")
            .setView(L)
            .setPositiveButton("ADD"){
                    dialog,which ->
                if(etNameAlert.text.isNotEmpty() && etT1Alert.text.isNotEmpty() && etT2Alert.text.isNotEmpty()
                    && etT3Alert.text.isNotEmpty()){
                    val Name = etNameAlert.text.toString()
                    val T1 = etT1Alert.text.toString()
                    val T2 = etT2Alert.text.toString()
                    val T3 = etT3Alert.text.toString()
                    myView1.addCelebrity(Celebrity(0,Name,T1,T2,T3))
                }else{
                    Toast.makeText(this,"Please enter values", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()

    }

    fun DeleteAlert(selectCelebrity: Celebrity){
        val alert =  AlertDialog.Builder(this)
        alert.apply {
            setTitle("Delete Celebrity")
            setMessage("Are you sure delete (${selectCelebrity.Name}) ?")
            setPositiveButton("DELETE"){
                    dialog,which ->
                myView1.deleteCelebrity(selectCelebrity)
            }
                .setNegativeButton("CANCEL"){
                        dialog,which -> dialog.cancel()
                }
                .show()
        }
    }


    fun UpdateAlert(selectCelebrity: Celebrity){
        val alert =  AlertDialog.Builder(this)
        val L = LinearLayout(this)
        val etNameAlert = EditText(this)
        etNameAlert.setText(selectCelebrity.Name)
        val etT1Alert = EditText(this)
        etT1Alert.setText(selectCelebrity.Taboo1)
        val etT2Alert = EditText(this)
        etT2Alert.setText(selectCelebrity.Taboo2)
        val etT3Alert = EditText(this)
        etT3Alert.setText(selectCelebrity.Taboo3)

        L.orientation = LinearLayout.VERTICAL
        L.apply {
            addView(etNameAlert)
            addView(etT1Alert)
            addView(etT2Alert)
            addView(etT3Alert)
        }

        alert.setTitle("Edit CelebrityModel")
            .setView(L)
            .setPositiveButton("UPDATE"){
                    dialog,which ->
                if(etNameAlert.text.isNotEmpty() && etT1Alert.text.isNotEmpty() && etT2Alert.text.isNotEmpty()
                    && etT3Alert.text.isNotEmpty()){
                    val ID = selectCelebrity.Id
                    val Name = etNameAlert.text.toString()
                    val T1 = etT1Alert.text.toString()
                    val T2 = etT2Alert.text.toString()
                    val T3 = etT3Alert.text.toString()
                    myView1.updateCelebrity(Celebrity(
                        ID,Name,T1,T2,T3
                    ))
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