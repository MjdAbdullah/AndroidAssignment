package com.example.flickrbrowserapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val KEY = "c852522d84e1390193d4d866171486b4"
    private var arr = arrayListOf<Image>()                  //Save Image data
    private lateinit var rvMain : RecyclerView
    lateinit var iv : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val bSearch = findViewById<Button>(R.id.bSearch)

        rvMain = findViewById(R.id.rvMain)
        iv = findViewById(R.id.imageView)
        // active bSearch button
        bSearch.setOnClickListener {
            //Check if the input is not empty
            if (etSearch.text.toString().isNotEmpty()){
                arr.clear()                                 // clear the array from old search
                CallApi(etSearch.text.toString())
            }else{
                Toast.makeText(this,"NO VALUE",Toast.LENGTH_LONG).show()
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    // for get the api call
    private fun CallApi(UserInput: String) {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val api = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=10&api_key=${KEY}&tags=${UserInput}&format=json&nojsoncallback=1")
                    .readText(Charsets.UTF_8)
                if (api.isNotEmpty()){
                    getData(api)
                }else {
                    Toast.makeText(this@MainActivity,"NULL VALUE",Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception){
                println("Error $e")
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    // read the json and send the array to RecyclerViewAdapter
    private suspend fun getData(api: String) {
        withContext(Dispatchers.Main){
            val photos = JSONObject(api).getJSONObject("photos").getJSONArray("photo")
            for (i in 0 until photos.length()){
                // for read the json
                val id = photos.getJSONObject(i).getString("id")
                val secret = photos.getJSONObject(i).getString("secret")
                val server = photos.getJSONObject(i).getString("server")
                val farm = photos.getJSONObject(i).getInt("farm")
                val title = photos.getJSONObject(i).getString("title")
                // add the data to array image
                arr.add(Image(title,"https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"))
            }
            // show the Recycler View
            rvMain.adapter = RecyclerViewAdapter(this@MainActivity,arr)
            rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMain.isVisible = true
        }
    }
}