package com.example.top100app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.RecyclerViewAdapter
import com.example.top10app.APIInterface
import com.example.top10app.Feed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity2 : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var rvMain2 : RecyclerView
    var arr100 = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rvMain2 = findViewById(R.id.rvMain2)

        val bGet = findViewById<Button>(R.id.bGet2)
        bGet.setOnClickListener {
            getFeeds()
            arr100.clear()
        }
    }
    //----------------------------------------------------------------------------------------------
    // for fetches the RSS (XML) feed
    fun getFeeds(){
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.apple.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
            val feedAPI = retrofit.create(APIInterface::class.java)

            feedAPI.feed100?.enqueue(object : Callback<Feed?> {
                override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                    Log.d(TAG, "onResponse: feed: " + response.body().toString())
                    Log.d(TAG, "onResponse: Server Response: ${response.body()}")

                    for (i in response.body()?.entrys!!) {
                        arr100.add(i.title.toString())
                    }

                    rvMain2.adapter = RecyclerViewAdapter(arr100)
                    rvMain2.layoutManager = LinearLayoutManager(this@MainActivity2)
                }

                override fun onFailure(call: Call<Feed?>, t: Throwable) {
                    Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                }
            })
        }
    }
    //----------------------------------------------------------------------------------------------
    //for menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item: MenuItem = menu!!.getItem(1)
        item.isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.To10 -> {
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}