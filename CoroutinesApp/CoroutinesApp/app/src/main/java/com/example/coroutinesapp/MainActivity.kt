package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {
    lateinit var tvAdvice : TextView
    var Pause = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bAdvice = findViewById<Button>(R.id.bAdvice)
        tvAdvice = findViewById(R.id.textView)
        var bPause = findViewById<Button>(R.id.bPause)

        bAdvice.setOnClickListener {
            Pause = true
            st()
        }
        bPause.setOnClickListener {
            Pause = false
        }
    }
    // ----------------------------------------------------------------------------------------
     fun st(){
        CoroutineScope(Dispatchers.IO).launch {
            while (Pause){
                // API call
                var call = URL("https://api.adviceslip.com/advice").readText(Charsets.UTF_8)
                call(call)
            }
        }
    }
    suspend fun call(Data: String) {
        withContext(Dispatchers.Main){
            // Data Call
                withContext(Dispatchers.Main){
                var slip = JSONObject(Data).getJSONObject("slip")
                var id = slip.getInt("id")
                var advice = slip.getString("advice")

                tvAdvice.text = advice.toString()
                }
        }
    }
}