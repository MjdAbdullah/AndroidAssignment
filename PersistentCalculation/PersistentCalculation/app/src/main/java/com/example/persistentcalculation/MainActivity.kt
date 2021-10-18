package com.example.persistentcalculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    companion object s {
        var result = 0.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val tv = findViewById<TextView>(R.id.textView)
        val et1 = findViewById<EditText>(R.id.etNumber1)
        val et2 = findViewById<EditText>(R.id.etNumber2)
        val b = findViewById<Button>(R.id.button)

        tv.text = result.toString()
        // Active the button
        b.setOnClickListener {
            result =   et1.text.toString().toDouble() * et2.text.toString().toFloat()
            tv.text = result.toString()
            et1.text.clear()
            et1.clearFocus()
            et2.text.clear()
            et2.clearFocus()
        }

    }
    //---------------------------------------------------------------------------------------------
    // for save value when rotating
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("result",result)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getDouble("savedInstanceState")
    }
}