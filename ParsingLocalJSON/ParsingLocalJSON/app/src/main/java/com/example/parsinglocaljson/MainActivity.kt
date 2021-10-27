package com.example.parsinglocaljson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.InputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    var arr = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)

        getJson()

        rvMain.adapter = RecyclerViewAdapter(this,arr)
        rvMain.layoutManager = LinearLayoutManager(this)

    }

    fun getJson(){
        var json : String? = null
        try{
            val iStream : InputStream = assets.open("data.json")
            json = iStream.bufferedReader().use { it.readText() }
            var jsonA = JSONArray(json)
            for (i in 0 until jsonA.length()){
                var jsonObj = jsonA.getJSONObject(i)
                arr.add(Image(jsonObj.getString("url")))
            }
        }catch (e: Exception){
            println("Error $e")
        }
    }

}