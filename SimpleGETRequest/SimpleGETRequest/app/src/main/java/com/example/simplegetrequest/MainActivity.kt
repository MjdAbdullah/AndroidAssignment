package com.example.simplegetrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    val Student = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMain = findViewById<RecyclerView>(R.id.rvMain)
        getAPI()

    }

    private fun getAPI() {
        val api = APIClient().getClient()?.create(APIInterface::class.java)
        api?.getData()?.enqueue(object : Callback<List<StudentDataItem>>{
            override fun onResponse(
                call: Call<List<StudentDataItem>>,
                response: Response<List<StudentDataItem>>,
            ) {
                for (i in response.body()!!){
                    Student.add(i.name)
                }
                rvMain.adapter = RecyclerViewAdapter(this@MainActivity,Student)
                rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<List<StudentDataItem>>, t: Throwable) {
                println("Error $t")
            }

        })
    }
}