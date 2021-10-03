package com.example.app2in1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var bNumberG : Button
    private lateinit var bPhraseG : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bNumberG = findViewById(R.id.bNumGame)
        bPhraseG = findViewById(R.id.bGuessG)

        bNumberG.setOnClickListener {
            val intent = Intent(this,NumberGame::class.java)
            startActivity(intent)
        }
        bPhraseG.setOnClickListener {
            val intent = Intent(this,GuessThePhrase::class.java)
            startActivity(intent)
        }
    }
    //for menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        val item: MenuItem = menu!!.getItem(0)
        val item1: MenuItem = menu!!.getItem(0)
        item.isVisible = false
        item1.isVisible = false
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.NumberGame -> {
                val intent = Intent(this,NumberGame::class.java)
                startActivity(intent)
            }
            R.id.PhraseGame -> {
                val intent = Intent(this,GuessThePhrase::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}