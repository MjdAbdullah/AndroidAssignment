package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.RecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private  val TAG = "MainActivity"
    private  val BASE_URL = "https://www.reddit.com/r/"
    lateinit var tvDes : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvNews = findViewById<TextView>(R.id.tvNews)
        tvDes = findViewById(R.id.tvDes)
        var rvMain = findViewById<RecyclerView>(R.id.rvMain)
        var arr = arrayListOf<data>()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        val feedAPI = retrofit.create(APIInterface::class.java)

        feedAPI.feed?.enqueue(object : Callback<Feed?> {
            override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                Log.d(TAG, "onResponse: feed: " + response.body().toString())
                Log.d(TAG, "onResponse: Server Response: ${response.body()}")

                tvNews.text = "News : ${response.body()?.updated}"
                for(i in response.body()?.entrys!!){
                    arr.add(data(i.title.toString(),i.author.toString()))
                }
                rvMain.adapter = RecyclerViewAdapter(this@MainActivity,arr)
                rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<Feed?>, t: Throwable) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
            }
        })

    }
}

data class data(val titel : String , val des : String)