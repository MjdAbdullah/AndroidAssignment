/*
*
* Numbers Game App
* Author : @MjdAbdullah
*
* */

package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    // inial
    lateinit var bEnter : Button
    lateinit var etNum : EditText
    lateinit var rvMain : RecyclerView
    lateinit var clMain : ConstraintLayout
    lateinit var myRV : RecyclerView
    var arr = arrayListOf<String>()
    var num = (0..10).random()
    var numGuess = 3 // number of attempts
    var state = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById<RecyclerView>(R.id.rvMain)
        etNum = findViewById<EditText>(R.id.etNumber)
        bEnter = findViewById<Button>(R.id.bEnter)
        clMain = findViewById<ConstraintLayout>(R.id.clMain)

        myRV = findViewById(R.id.rvMain)
        myRV.adapter = RecyclerViewAdapter(this, arr)
        myRV.layoutManager = LinearLayoutManager(this)

        // Active button 'Enter'
        bEnter.setOnClickListener {
            // Check the number of attempts > Actually, this part is not necessary
            // I just wanted to try it. :)
            // we can just dependence on the (IF) in line 85 .
            if(numGuess != 0) {
                play()
            }
        }
    }//fun onCreate

    fun play(){
        val UserGuess = etNum.text.toString()
        // Check if the input is Empty
        if(UserGuess.isEmpty()){
            Snackbar.make(clMain,"Please Enter number !!",Snackbar.LENGTH_SHORT).show()
        }else {
            // Check if the number is between 0 and 10
            if(UserGuess.toInt() > 10 || UserGuess.toInt() < 0 ){
                Snackbar.make(clMain,"Please just Enter number between 0 and 10!!",Snackbar.LENGTH_SHORT).show()
            }else{
                numGuess -= 1
                // Check if the user guess is correct or not
                if(UserGuess.toInt() == num){
                    arr.add("You guessed $UserGuess, is correct.")
                    arr.add("You have $numGuess guess left")
                    myRV.adapter = RecyclerViewAdapter(this, arr)
                    Snackbar.make(clMain,"Correct Answer .. Great!",Snackbar.LENGTH_SHORT).show()
                    state = "You WON!"
                    ShowAlert()
                }else{
                    arr.add("You guessed $UserGuess, is wrong.")
                    arr.add("You have $numGuess guess left")
                    myRV.adapter = RecyclerViewAdapter(this, arr)
                    Snackbar.make(clMain,"Sorry! wrong guess :(",Snackbar.LENGTH_SHORT).show()
                }//else
            }//else
        }//else
        // Check If the number of attempts is end
        if(numGuess == 0){
            state = "You Lose the number was $num"
            ShowAlert()
        }
        etNum.text.clear()
        etNum.clearFocus()
    }//fun play

    // Bonus .
    // show alert dialog to replay or end game !
    private fun ShowAlert(){
        val alert =  AlertDialog.Builder(this)
        val alert2 =  AlertDialog.Builder(this)
        alert.setMessage("$state\nDo you want play more ?") // Print message for user
            .setTitle("Number Game App") // the title of alert
            .setCancelable(false)
                // if user click yes >> recreate
            .setPositiveButton("Yes"){
                    dialog, which -> Snackbar.make(clMain,"Game repaly .. ANGOY!",Snackbar.LENGTH_SHORT).show()
                    this.recreate()
            }
            // if user click no >> close the app
            .setNegativeButton("No"){
                    dialog, which -> Snackbar.make(clMain,"GomeOver .. GOODBYE!",Snackbar.LENGTH_SHORT).show()
                // I know it's a stupid part but I just added it for more practice :$
                // it can be like :  (You want to play more or exit?) with (play/exit) choose.
                alert2.setMessage("Want Exit?")
                    .setPositiveButton("Yes"){
                            dialog, which -> finish()
                    }
                    .setNegativeButton("No"){
                            dialog, which -> Snackbar.make(clMain,"OK :)",Snackbar.LENGTH_SHORT).show()
                    }
                    .show()
            }
            .show()
    }// un ShowAlert
}// class Main