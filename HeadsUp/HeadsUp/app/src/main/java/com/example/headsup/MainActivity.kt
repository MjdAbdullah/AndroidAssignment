package com.example.headsup

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Surface
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var tvName: TextView
    lateinit var tvT1: TextView
    lateinit var tvT2: TextView
    lateinit var tvT3: TextView
    lateinit var lMain: LinearLayout
    lateinit var lC: LinearLayout

    lateinit var tvTime: TextView
    lateinit var tvMain: TextView
    lateinit var bStart: Button

    var GameState = 0
    var celebrities= arrayListOf<JSONObject>()

    var num = 0

    var TimerStart : Long = 20000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lMain = findViewById(R.id.lMain)
        lC = findViewById(R.id.lC)

        tvTime = findViewById(R.id.tvTime)

        tvName = findViewById(R.id.tvName)
        tvT1 = findViewById(R.id.tvT1)
        tvT2 = findViewById(R.id.tvT2)
        tvT3 = findViewById(R.id.tvT3)

        tvMain = findViewById(R.id.tvMain)
        bStart = findViewById(R.id.bStart)

        bStart.setOnClickListener {
            bStart.isVisible =false
            GameState = 1
            API()
            Timer()
            tvMain.text = "Please Rotate Device"
        }
    }

    private fun API(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var api = ""
                api =
                    URL("https://dojo-recipes.herokuapp.com/celebrities/")
                        .readText(Charsets.UTF_8)
                parseJSON(api)
                println("here")
            }catch (e: Exception){
                println("Error: $e")
            }
        }
    }

    private suspend fun parseJSON(result: String){
        withContext(Dispatchers.Main){
            celebrities.clear()
            val arr = JSONArray(result)
            for(i in 0..arr.length()-1){
                celebrities.add(arr.getJSONObject(i))

            }
        }
    }

    private fun Timer(){
            object : CountDownTimer(TimerStart, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    TimerStart = millisUntilFinished
                    tvTime.text = "Time: ${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    TimerStart = 20000
                    GameState = 0
                    tvTime.text = "Time: --"
                    tvMain.text = "Heads Up!"
                    bStart.isVisible = true
                    lC.isVisible = false
                    lMain.isVisible = true
                }
            }.start()
    }

    private fun newCelebrity(){
        if(num < celebrities.size){
            tvName.text = celebrities[num].getString("name")
            tvT1.text = celebrities[num].getString("taboo1")
            tvT2.text = celebrities[num].getString("taboo2")
            tvT3.text = celebrities[num].getString("taboo3")
        }else{
            num = 0
        }
    }

    //---------------------------------------------------------------------------------------------
    //for ROTATION
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            if(GameState == 1){
                newCelebrity()
                num++
                lC.isVisible = true
                lMain.isVisible = false
                bStart.isVisible = false
                tvMain.text = "Please Rotate Device"
                Timer()
            }
        }else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT){
            if(GameState == 1){
                lC.isVisible = false
                lMain.isVisible = true
                bStart.isVisible = false
                tvMain.isVisible = true
                bStart.isVisible = false
                tvMain.text = "Please Rotate Device"
                Timer()

            }else{
                lC.isVisible = false
                lMain.isVisible = true
                bStart.isVisible = true
                tvMain.isVisible = true
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("GameState", GameState)
        outState.putLong("TimerStart",TimerStart)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        GameState = savedInstanceState.getInt("GameState")
        TimerStart = savedInstanceState.getLong("TimerStart")
    }
}