package com.example.app2in1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class GuessThePhrase : AppCompatActivity() {

    var PhraseList = arrayListOf<String>()      // List of phrase
    var LetterList = arrayListOf<Char>()        // List of all letters the user is use it
    var CprrectLetter = "Guessed Letters: "       // String for correct letters
    var arr = arrayListOf<String>()             // Array for print
    lateinit var bGuuess : Button
    lateinit var tvPhrase : TextView
    lateinit var tvLetters : TextView
    lateinit var rvMain : RecyclerView
    lateinit var clMain : ConstraintLayout
    lateinit var etUserGusess : EditText
    lateinit var tvScore : TextView
    var AttemptsLe = 10    // The number of guess attempts the litters
    var AttemptsPa = 10    // The number of guess attempts the Phrase
    var Phrase = ""
    var UserGuess = ""
    var state = ""         //User state
    var PhraseDisplay = ""
    val PhraseDictionary = mutableMapOf<Int, Char>()
    private lateinit var sharedPreferences: SharedPreferences
    var Score = 0           // variable to save the value of score.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_the_phrase)
        // Most famous phrase that i found it .
        PhraseList.add("To cut corners")    // To do something badly or cheaply.
        PhraseList.add("Hang in there")     // Don't give up
        PhraseList.add("A piece of cake")  // Something is very easy.
        PhraseList.add("No pain no gain")      // You have to work for what you want
        PhraseList.add("Under the weather") // To not feel well.
        PhraseList.add("When pigs fly")     // Something that will never happen.
        PhraseList.add("See eye to eye")    // This means agreeing with someone.
        PhraseList.add("Speak of the devil")  // When you take about someone and he appears at that moment.

        Phrase = (PhraseList[Random.nextInt(0,9)]).toLowerCase()  // Select a phrase random from a list
        for(i in Phrase.indices){
            if (Phrase[i] == ' '){
                PhraseDictionary[i] = ' '
                PhraseDisplay += ' '
            }else{
                PhraseDisplay += '*'
                PhraseDictionary[i] = '*'
            }
        }

        bGuuess = findViewById<Button>(R.id.bGuess)
        tvPhrase = findViewById<TextView>(R.id.tvPhrase)
        tvPhrase.text = PhraseDisplay                   // Show user the Phrase

        tvLetters = findViewById<TextView>(R.id.tvLetters)
        tvLetters.text = CprrectLetter                    // Show user list of correct litter.

        tvScore = findViewById(R.id.tvScore)
        etUserGusess = findViewById<EditText>(R.id.etUserGusess)
        etUserGusess.hint = "Guess the full phrase letter"      // Show to user a hint that ask him to enter letter

        rvMain = findViewById<RecyclerView>(R.id.rvMain)
        rvMain.adapter = RecyclerViewAdapter(this, arr)
        rvMain.layoutManager = LinearLayoutManager(this)

        clMain = findViewById(R.id.clAGPhrase)
        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        Score = sharedPreferences.getInt("Score",Score).toInt()

        // Listener for GUESS button
        bGuuess.setOnClickListener {
            UserGuess = etUserGusess.text.toString().toLowerCase()
            // Check if the input is empty
            if(UserGuess.isEmpty()){
                Snackbar.make(clMain,"Enter something", Snackbar.LENGTH_SHORT).show()
            }else{
                // filter the input from the Space
                UserGuess = UserGuess.filterNot { it.equals(' ') }
                //Check if user input alphabets or number
                if(!isAlphabets(UserGuess)){
                    Snackbar.make(clMain,"Allow enter just Alphabets", Snackbar.LENGTH_SHORT).show()
                }else{
                    //check if the user should letter or full phrase
                    when(etUserGusess.hint){
                        "Guess the letter" -> {
                            if(UserGuess.length-1 > 1){
                                Snackbar.make(clMain,"Allow enter just the guess of Letter",
                                    Snackbar.LENGTH_SHORT).show()
                            }else if(!(Available(UserGuess[0]))){
                                Snackbar.make(clMain,"You Guess this letter before, Try again",
                                    Snackbar.LENGTH_SHORT).show()
                            }else{
                                GuessLetter(UserGuess[0])
                            }
                        }
                        "Guess the full phrase letter" -> {
                            if(UserGuess.length-1 < 3){
                                Snackbar.make(clMain,"To short to be phrase", Snackbar.LENGTH_SHORT).show()
                            }else{
                                GuessPhrase()
                            }
                        }
                    }//when
                }
            }
        }//ClickListener
        UpdateText()
    }
    // --------------------------------------------------------------------------------------------
    //Preserve the state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("AttemptsPa",AttemptsPa)
        outState.putInt("AttemptsLe",AttemptsLe)
        outState.putString("Phrase",Phrase)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Phrase = savedInstanceState.get("Phrase") as String
        AttemptsLe = savedInstanceState.getInt("AttemptsLe")
        AttemptsPa = savedInstanceState.getInt("AttemptsPa")
    }
    // --------------------------------------------------------------------------------------------
    // this function for check the user guest, if it correct will end game and show alert,
    // else will decrease the number of attempts and print it in the rvMain.
    fun GuessLetter(GuessChar : Char){
        AttemptsLe--
        LetterList.add(GuessChar)
        if(Phrase.contains(GuessChar)){
            CprrectLetter += "$GuessChar, "
            var num = 0
            for(i in Phrase.indices){
                if(Phrase[i] == GuessChar){
                    PhraseDictionary[i] = GuessChar
                    num++
                }
            }
            PhraseDisplay = ""
            for (i in PhraseDictionary){
                PhraseDisplay += PhraseDictionary[i.key]
            }
            tvPhrase.text = PhraseDisplay.toUpperCase()
            tvLetters.text = CprrectLetter
            if (PhraseDisplay.filter { it.equals(' ') } == Phrase){
                state = "WON"
                Score += 10         //get 10 score if you won.
                save()
                ShowAlert()
            }
            etUserGusess.hint = "Guess the full phrase letter"
            arr.add("Found $num ${GuessChar.toUpperCase()}(s)")
            rvMain.adapter = RecyclerViewAdapter(this, arr)
        }else{
            arr.add("Wrong guess: ${GuessChar.toUpperCase()}")
            rvMain.adapter = RecyclerViewAdapter(this, arr)
        }
        if(AttemptsLe == 0){
            state = "LOSE"
            save()
            ShowAlert()
        }
        arr.add("$AttemptsLe guesses remaining")
        rvMain.adapter = RecyclerViewAdapter(this, arr)
        etUserGusess.text.clear()
        etUserGusess.clearFocus()
        if(AttemptsLe == 0){
            state = "LOSE"
            save()
            ShowAlert()
        }
    }//GuessLetter()
    // -------------------------------------------------------------------------------------------
    // this function for check the user guest, if it correct will end game and show alert,
    // else will decrease the number of attempts and print it in the rvMain.
    fun GuessPhrase(){
        if(Phrase.filterNot { it.equals(' ')} == UserGuess){
            state = "WON"
            Score += 10         //get 10 score if you won.
            save()
            UpdateText()
            ShowAlert()
        }else{
            AttemptsPa--  // decrease the number of attempts
            arr.add("Worng guess: ${etUserGusess.text}")
            rvMain.adapter = RecyclerViewAdapter(this, arr)
            etUserGusess.hint = "Guess the letter"
        }
        etUserGusess.text.clear()
        etUserGusess.clearFocus()
        if(AttemptsPa == 0){
            state = "Lose"
            save()
            ShowAlert()
        }
    }//GuessPhrase()
    // -------------------------------------------------------------------------------------------
    // This function call when game end and ask the user if he want play more or exit.
    private fun ShowAlert(){
        val alert =  AlertDialog.Builder(this)
        alert.setMessage("Phresa: ${Phrase.capitalize()}\nYou $state!!\nWant play more?")
            .setTitle("Guess the phrase")
            .setCancelable(false)
            .setPositiveButton("Yes"){
                    dialog,which -> Snackbar.make(clMain,"Game Start .. ANGOY!",Snackbar.LENGTH_SHORT).show()
                this.recreate()
            }
            .setNegativeButton("Exit") {
                    dialog,which -> Snackbar.make(clMain,"GOODBYE!",Snackbar.LENGTH_SHORT).show()
                finish()
            }
            .show()
    }//ShowAlert()
    // -------------------------------------------------------------------------------------------
    // This function to check if the user input is alphabets.
    fun isAlphabets(S : String): Boolean{
        for(c in S){
            if (c !in 'a'..'z') {
                return false
            }
        }
        return true
    }//isAlphabet
    // -------------------------------------------------------------------------------------------
    // This function to check if the user guess this letter before.
    fun Available(C:Char):Boolean{
        for(c in LetterList){
            if(c == C){
                return false
            }
        }
        return true
    }//Available
    // -------------------------------------------------------------------------------------------
    // To save the score
    fun save(){
        with(sharedPreferences.edit()) {
            putInt("Score", Score)
            apply()
        }
    }
    // -------------------------------------------------------------------------------------------
    // For update the text in interface
    fun UpdateText(){
        tvScore.text = "Score: $Score "
    }
    // -------------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        val item: MenuItem = menu!!.getItem(2)
        item.isVisible = false
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.New -> {
                ShowAlert()         //Call the fun
            }
            R.id.Main -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.NumberGame -> {
                save()          //For save the score
                val intent = Intent(this,NumberGame::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}