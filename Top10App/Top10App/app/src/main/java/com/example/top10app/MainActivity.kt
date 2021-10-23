package com.example.top10app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var rvMain : RecyclerView
    var arr = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)

        val bGet = findViewById<Button>(R.id.bGet)
        bGet.setOnClickListener {
            getFeeds()
        }
    }

    fun getFeeds(){
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.apple.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
            val feedAPI = retrofit.create(APIInterface::class.java)

            feedAPI.feed?.enqueue(object : Callback<Feed?> {
                override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                    Log.d(TAG, "onResponse: feed: " + response.body().toString())
                    Log.d(TAG, "onResponse: Server Response: ${response.body()}")

                    for (i in response.body()?.entrys!!) {
                        arr.add(i.title.toString())
                    }

                    rvMain.adapter = RecyclerViewAdapter(arr)
                    rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                override fun onFailure(call: Call<Feed?>, t: Throwable) {
                    Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                }
            })
        }
    }

}
