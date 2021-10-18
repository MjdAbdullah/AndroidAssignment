package com.example.recyclerviewactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val rvMain = findViewById<RecyclerView>(R.id.rvMain)

        var arr = ArrayList<String>()

        val intent = getIntent()
        arr.add(intent.getStringExtra("et1").toString())
        arr.add(intent.getStringExtra("et2").toString())
        arr.add(intent.getStringExtra("et3").toString())
        arr.add(intent.getStringExtra("et4").toString())

        rvMain.adapter = RecyclerViewAdapter(this@MainActivity2, arr)
        rvMain.layoutManager = LinearLayoutManager(this@MainActivity2)
    }
}