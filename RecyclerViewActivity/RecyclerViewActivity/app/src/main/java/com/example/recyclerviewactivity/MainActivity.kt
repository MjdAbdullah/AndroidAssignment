package com.example.recyclerviewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b = findViewById<Button>(R.id.button)
        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val et3 = findViewById<EditText>(R.id.et3)
        val et4 = findViewById<EditText>(R.id.et4)
        b.setOnClickListener {
            if(et1.text.toString().isNotEmpty() && et2.text.toString().isNotEmpty() && et3.text.toString().isNotEmpty()
                && et4.text.toString().isNotEmpty()) {

                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("et1", et1.text.toString())
                intent.putExtra("et2", et2.text.toString())
                intent.putExtra("et3", et3.text.toString())
                intent.putExtra("et4", et4.text.toString())
                startActivity(intent)

            }else{
                Toast.makeText(this,"NO VALUE",Toast.LENGTH_LONG).show()
            }
        }
    }
}