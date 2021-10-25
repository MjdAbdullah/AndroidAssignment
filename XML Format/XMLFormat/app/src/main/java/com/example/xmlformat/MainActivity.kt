package com.example.xmlformat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvMain = findViewById<RecyclerView>(R.id.rvMain)
        var Students : List<Student>

        try {
            val parser = XmlPullParserHandler()
            val istream = assets.open("student.xml")
            Students =  parser.parse(istream)

            rvMain.adapter = RecyclerViewAdapter(this,Students)
            rvMain.layoutManager = LinearLayoutManager(this)

        }catch (e:Exception){
            e.printStackTrace()
            println("Error: $e")
        }

    }
}