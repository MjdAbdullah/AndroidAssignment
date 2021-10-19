package com.example.companionobjectsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    companion object{
        var input = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.textView)
        val etValue = findViewById<EditText>(R.id.etVaule)
        val bChange = findViewById<Button>(R.id.bChange)
        val iv = findViewById<ImageView>(R.id.imageView)

        bChange.setOnClickListener {
            // first check if is not empty
            if(etValue.text.isNotEmpty()){
                input = etValue.text.toString()
                input = input.replace("\\s".toRegex(), "")    //remove all whitespace
                when(input.toLowerCase()){
                    "day" -> {
                        iv.setImageResource(R.drawable.day)
                        etValue.setTextColor(Color.BLACK)
                        tv.setTextColor(Color.BLACK)
                    }
                    "night" ->{
                        iv.setImageResource(R.drawable.night)
                        etValue.setTextColor(Color.WHITE)
                        tv.setTextColor(Color.WHITE)
                    }
                    else -> Toast.makeText(this,"Please enter Day or Night only",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"NO VALUE",Toast.LENGTH_LONG).show()
            }
        }

    }


}