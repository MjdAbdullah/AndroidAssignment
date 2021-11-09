package com.example.headsup2

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Surface
import android.view.TextureView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class Start : AppCompatActivity() {

    lateinit var tvTime : TextView
    lateinit var tvText : TextView
    var arr = ArrayList<CelebrityModel>()
    lateinit var tvName : TextView
    lateinit var tvT1: TextView
    lateinit var tvT2: TextView
    lateinit var tvT3: TextView
    lateinit var lc : LinearLayout

    companion object{
        var num : Long = 20000
        var count = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        tvTime = findViewById(R.id.tvTime)
        tvText = findViewById(R.id.tvText)
        tvName = findViewById(R.id.tvName)
        tvT1 = findViewById(R.id.tvT1)
        tvT2 = findViewById(R.id.tvT2)
        tvT3 = findViewById(R.id.tvT3)
        lc = findViewById(R.id.lC)

        val DB = DBHlpr(applicationContext)
        arr = DB.getData()
        Timer()
    }
    //----------------------------------------------------------------------------------------------
    // Timer function
    private fun Timer(){
        object : CountDownTimer(num, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                num = millisUntilFinished
                tvTime.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                num = 20000
                End()
            }
        }.start()
    }
    //----------------------------------------------------------------------------------------------
    // When the timer end will open MainActivity
    fun End(){
        startActivity(Intent(this,MainActivity::class.java))
    }
    //----------------------------------------------------------------------------------------------
    // to Display the data
    private fun newCelebrity(){
        if(count < arr.size){
            tvName.text = arr[count].Name
            tvT1.text = arr[count].Taboo1
            tvT2.text = arr[count].Taboo2
            tvT3.text = arr[count].Taboo3
            count++
        }else{
            count = 0
        }
    }
    //---------------------------------------------------------------------------------------------
    //FOR LANDSCAPE,PORTRAIT
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            newCelebrity()
            lc.isVisible = true
            tvText.isVisible = false
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            lc.isVisible = false
            tvText.isVisible = true
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count",count)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("count")

    }

}