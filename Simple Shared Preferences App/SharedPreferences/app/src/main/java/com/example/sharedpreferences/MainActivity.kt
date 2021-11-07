package com.example.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val etName = findViewById<EditText>(R.id.etName)
        val etLocation = findViewById<EditText>(R.id.etLocation)
        val bIntent = findViewById<Button>(R.id.bIntent)
        val bSave = findViewById<Button>(R.id.bSave)

        bIntent.setOnClickListener {
            if(etName.text.isNotEmpty() && etLocation.text.isNotEmpty() ){
                val intent = Intent(this,MainActivity2::class.java)
                intent.putExtra("Name", etName.text.toString())
                intent.putExtra("Location", etLocation.text.toString())
                etName.text.clear()
                etName.clearFocus()
                etLocation.text.clear()
                etLocation.clearFocus()
                startActivity(intent)
            }else{
                Toast.makeText(this,"Please enter values", Toast.LENGTH_SHORT).show()
            }
        }

        bSave.setOnClickListener {
            if(etName.text.isNotEmpty() && etLocation.text.isNotEmpty() ){
                var name = etName.text.toString()
                var location = etLocation.text.toString()
                with(sharedPreferences.edit()){
                    putString("Name", name)
                    putString("Location", location)
                    apply()
                }
                etName.text.clear()
                etName.clearFocus()
                etLocation.text.clear()
                etLocation.clearFocus()
            }else{
                Toast.makeText(this,"Please enter values", Toast.LENGTH_SHORT).show()
            }
        }
    }
}