package com.example.guessthephrasesqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddPhrase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_phrase)

        val etPhrase = findViewById<EditText>(R.id.etPhrase)
        val bAdd = findViewById<Button>(R.id.bAdd)
        val DB = DBHlpr(applicationContext)

        bAdd.setOnClickListener {
            if(etPhrase.text.isNotEmpty()){
                var state = DB.putData(etPhrase.text.toString())
                Toast.makeText(applicationContext,"Data saved successfully !\n$state", Toast.LENGTH_SHORT).show()
                etPhrase.text.clear()
            }else{
                Toast.makeText(this,"NO VALUE", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        val item: MenuItem = menu!!.getItem(0)
        item.isVisible = false
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Play -> {
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}