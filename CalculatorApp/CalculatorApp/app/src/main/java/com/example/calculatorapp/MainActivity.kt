package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var tvView : TextView
    private lateinit var tvOpr : TextView
    private lateinit var clMain : ConstraintLayout
    var Ruselt = ""
    private var TheOpr = ""
    var Decimal = false
    var PN = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvView = findViewById(R.id.tvRuselt)
        tvOpr = findViewById(R.id.tvOpr)
        clMain = findViewById(R.id.clMain)
        val b1 = findViewById<Button>(R.id.b1)
        val b2 = findViewById<Button>(R.id.b2)
        val b3 = findViewById<Button>(R.id.b3)
        val b4 = findViewById<Button>(R.id.b4)
        val b5 = findViewById<Button>(R.id.b5)
        val b6 = findViewById<Button>(R.id.b6)
        val b7 = findViewById<Button>(R.id.b7)
        val b8 = findViewById<Button>(R.id.b8)
        val b9 = findViewById<Button>(R.id.b9)
        val bZero = findViewById<Button>(R.id.bZero)
        b1.setOnClickListener { UpdateText("1",0) }
        b2.setOnClickListener { UpdateText("2",0) }
        b3.setOnClickListener { UpdateText("3",0) }
        b4.setOnClickListener { UpdateText("4",0) }
        b5.setOnClickListener { UpdateText("5",0) }
        b6.setOnClickListener { UpdateText("6",0) }
        b7.setOnClickListener { UpdateText("7",0) }
        b8.setOnClickListener { UpdateText("8",0) }
        b9.setOnClickListener { UpdateText("9",0) }
        bZero.setOnClickListener { UpdateText("0",0) }

        val bAdd = findViewById<Button>(R.id.bAdd)
        bAdd.setOnClickListener {
            UpdateText("+",0)
        }

        val bSub = findViewById<Button>(R.id.bSub)
        bSub.setOnClickListener {
            UpdateText("-",0)
        }

        val bDiv = findViewById<Button>(R.id.bDivision)
        bDiv.setOnClickListener {
            UpdateText("/",0)
        }

        val bMult = findViewById<Button>(R.id.bMult)
        bMult.setOnClickListener {
            UpdateText("*",0)
        }

        val bDel = findViewById<Button>(R.id.bDelet)
        bDel.setOnClickListener{ UpdateText("",1) }

        val bClear = findViewById<Button>(R.id.bClear)
        bClear.setOnClickListener {
            TheOpr = ""
            tvView.text = ""

        }

        val bPN = findViewById<Button>(R.id.bPN)
        bPN.setOnClickListener {
            PN = true
            UpdateText("-",0)
            PN = false
        }

        val bDot = findViewById<Button>(R.id.bDot)
        bDot.setOnClickListener {
            if (!Decimal){
                UpdateText(".",0)
            }
            Decimal = true
        }

        val bRuselt = findViewById<Button>(R.id.bRuselt)
        bRuselt.setOnClickListener {
            if(Decimal){
                Ruselt = Calculator(TheOpr).toString()
            }else{
                val temp = Calculator(TheOpr).toInt()
                Ruselt = temp.toString()
            }
            UpdateText("",3)
        }
    }// onCreate
    //---------------------------------------------------------------------------------------------
    // This function for update the text view
    private fun UpdateText(string : String , opre : Int ) {
        // 0 for add in the text , 1 for remove from the text
        when(opre){
            0 -> {
                tvView.text = tvView.text.toString() + string
                if (PN){
                    TheOpr = TheOpr + string
                }else{
                    when(string){
                        "+" -> TheOpr = TheOpr + "A"
                        "-" -> TheOpr = TheOpr + "S"
                        else -> TheOpr = TheOpr + string
                    }
                }
            }
            1 ->{
                if (tvView.text.length == 1){
                    TheOpr = ""
                    tvView.text = ""
                }else {
                    tvView.text = tvView.text.substring(0, tvView.text.length - 1)
                    TheOpr = TheOpr.substring(0, tvView.text.length - 1)
                }
            }
            3 -> {
                tvOpr.text =  tvView.text
                TheOpr = Ruselt
                tvView.text = Ruselt
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    // This function
    private fun Calculator(S : String): Double{
            for(i in 0..S.length-1){
                if(S[i] == 'A'){
                    return Calculator(S.substring(0,i)) + Calculator(S.substring(i+1,S.length))
                }
            }
            for(i in 0..S.length-1){
                if(S[i] == 'S'){
                    return Calculator(S.substring(0,i)) - Calculator(S.substring(i+1,S.length))
                }
            }
            for(i in 0..S.length-1){
                if(S[i] == '*'){
                    return Calculator(S.substring(0,i)) * Calculator(S.substring(i+1,S.length))
                }
            }
            for(i in 0..S.length-1){
                if(S[i] == '/'){
                    if (S.substring(i+1,S.length).equals("0")){
                        Snackbar.make(clMain,"Can't Divide by 0",Snackbar.LENGTH_SHORT).show()
                        Ruselt = "0"
                        return 0.0
                    }else{
                        return Calculator(S.substring(0,i)) / Calculator(S.substring(i+1,S.length))
                    }
                }
            }
            return S.toDouble()
    }//Calculator
    //----------------------------------------------------------------------------------------------
    //Preserve the state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("TheOpr",TheOpr)
        outState.putString("Show",tvView.text.toString())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        TheOpr = savedInstanceState.getString("TheOpr").toString()
        tvView.text = savedInstanceState.getString("Show")
    }
}//Main