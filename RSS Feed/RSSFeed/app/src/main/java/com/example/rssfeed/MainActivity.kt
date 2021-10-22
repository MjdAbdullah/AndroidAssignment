package com.example.rssfeed

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var rvMain: RecyclerView
    var Questions = arrayListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        FetchTopSongs().execute()

    }

    private inner class FetchTopSongs : AsyncTask<Void, Void, MutableList<Song>>() {
        val parser = XMLParser()
        override fun doInBackground(vararg params: Void?): MutableList<Song> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            Questions =
                urlConnection.getInputStream()?.let {
                    parser.parse(it)
                }
                        as ArrayList<Song>
            return Questions
        }

        override fun onPostExecute(result: MutableList<Song>?) {
            super.onPostExecute(result)
            rvMain.adapter = RecyclerViewAdapter(this@MainActivity ,Questions)
            rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }
}