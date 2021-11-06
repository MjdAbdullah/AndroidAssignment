package com.example.debuggingchallenge3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.net.URL
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val definitions = arrayListOf<ArrayList<String>>()

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private lateinit var etWord: EditText
    private lateinit var btSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(definitions)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        etWord = findViewById(R.id.etWord)
        btSearch = findViewById(R.id.btSearch)
        btSearch.setOnClickListener {
            requestAPI()
        }
    }
    /*
    Fix the debugging from this project

    The layout files:
    https://github.com/AlminPiricDojo/DebugChallenge3/tree/8609e70c743e14ce3da415ff106b0ee7fa99d161/app/src/main/res/layout

    Main Activity and Recycler View Adapter:
    https://github.com/AlminPiricDojo/DebugChallenge3/tree/8609e70c743e14ce3da415ff106b0ee7fa99d161/app/src/main/java/com/example/debugchallenge3
     */
    private fun requestAPI(){
        if(etWord.text.isNotEmpty()){
            CoroutineScope(IO).launch {
                val data = async{
                    getDefinition(etWord.text.toString())
                }.await()
                if(data.isNotEmpty()){
                    updateRV(data)
                }else{
                    //the app crashes when an invalid word is entered
                     //Fix it
                    withContext(Main){
                        Toast.makeText(this@MainActivity, "Unable to get data", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }else{
            //the Toast message never appears
            //Fix it
            Toast.makeText(this, "Please enter a word", Toast.LENGTH_LONG).show()
        }
    }

    private fun getDefinition(word: String): String{
        var response = ""
        try {
            //the API always thinks the user has entered the word 'house'
                // Fix it
            response = URL("https://api.dictionaryapi.dev/api/v2/entries/en/$word").readText(Charsets.UTF_8)
        }catch (e: Exception){
            //the app crashes if there is no internet connection
            //Fix it
        println("Error: $e")
        }
        return response
    }

    private suspend fun updateRV(result: String){
        withContext(Main){
            Log.d("MAIN", "DATA: $result")

            val jsonArray = JSONArray(result)
            val main = jsonArray[0]
            val word = JSONObject(main.toString()).getString("word")
            //the definition value is wrong
            //Fix it
            val inside = jsonArray.getJSONObject(0).getJSONArray("meanings")
                .getJSONObject(0).getJSONArray("definitions").getJSONObject(0)
            val definition = JSONObject(inside.toString()).getString("definition")
            Log.d("MAIN", "WORD: $word $definition")
            definitions.add(arrayListOf(word, definition))
            rvAdapter.update()
            etWord.text.clear()
            etWord.clearFocus()
            rvMain.scrollToPosition(definitions.size - 1)
        }
    }
}