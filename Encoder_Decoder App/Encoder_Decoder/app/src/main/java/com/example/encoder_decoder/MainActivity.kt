package com.example.encoder_decoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var arr = arrayListOf<Phrase>()
    lateinit var rvMain: RecyclerView
    lateinit var RV : RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEncoder = findViewById<EditText>(R.id.etEncoder)
        val bEncoder = findViewById<Button>(R.id.bEncoder)
        val etDecoder = findViewById<EditText>(R.id.etDecoder)
        val bDecoder = findViewById<Button>(R.id.bDecoder)
        rvMain = findViewById(R.id.rvMain)
        RV = RVAdapter(this,arr)
        rvMain.adapter = RV
        rvMain.layoutManager = LinearLayoutManager(this)

        bEncoder.setOnClickListener {
            if (etEncoder.text.isNotEmpty()){
                Encoder_Decoder(etEncoder.text.toString(),true)
                etEncoder.text.clear()
                etEncoder.clearFocus()
            }else{
                Toast.makeText(this,"Please enter phrase",Toast.LENGTH_SHORT).show()
            }
        }

        bDecoder.setOnClickListener {
            if (etDecoder.text.isNotEmpty()){
                Encoder_Decoder(etDecoder.text.toString(),false)
                etDecoder.text.clear()
                etDecoder.clearFocus()
            }else{
                Toast.makeText(this,"Please enter phrase",Toast.LENGTH_SHORT).show()
            }
        }

    }
    //----------------------------------------------------------------------------------------------
    fun Encoder_Decoder(phrase: String, I: Boolean) {
        var newPhrase = ""
        for (letter in phrase){
            if(isAlphabetSmall(letter)){
                newPhrase += ChangeLetter(letter.toLowerCase(),I)
            }else if(isAlphabetCapital(letter)){
                newPhrase += ChangeLetter(letter.toLowerCase(),I).toUpperCase()
            }else{
                newPhrase += letter
            }
        }
        arr.add(Phrase(phrase,newPhrase))
        RV.Update(arr)
    }
    //----------------------------------------------------------------------------------------------
    // Change the Letters
    private fun ChangeLetter(letter: Char, I: Boolean): Char {
        var c = letter
        //IF it true -> Encoder -> +13
        if(I){
            for(i in 1..13){
                if(c == 'z'){
                    c = 'a'
                }else{
                    c++
                }
            }
            return c
        }else{//IF it false -> Decoder -> -13
            for (i in 1..13){
                if(c == 'a'){
                    c = 'z'
                }else{
                    c--
                }
            }
            return c
        }
    }
    //----------------------------------------------------------------------------------------------
    // For Check if the letter small
    private fun isAlphabetSmall(letter: Char): Boolean {
        for(i in 'a'..'z'){
            if(letter == i){
                return true
            }
        }
        return false
    }
    //----------------------------------------------------------------------------------------------
    // For Check if the letter Capital
    private fun isAlphabetCapital(letter: Char): Boolean {
        for(i in 'A'..'Z'){
            if(letter == i){
                return true
            }
        }
        return false
    }

}