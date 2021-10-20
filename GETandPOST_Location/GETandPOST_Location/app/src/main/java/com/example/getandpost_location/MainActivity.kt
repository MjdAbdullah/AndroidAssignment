package com.example.getandpost_location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.recipeapp.APIClient
import com.example.recipeapp.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val api = APIClient().getClient()?.create(APIInterface::class.java)
    lateinit var tvLocation : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etLocation = findViewById<EditText>(R.id.etLocation)
        val etSName = findViewById<EditText>(R.id.etSName)
        val bSave = findViewById<Button>(R.id.bSave)
        val bSearch = findViewById<Button>(R.id.bSearch)
        tvLocation = findViewById(R.id.tvLocation)

        bSave.setOnClickListener {
            // Check the Edit text is Empty
            if (etName.text.isNotEmpty() && etLocation.text.isNotEmpty()){
                AddData(etName.text.toString(), etLocation.text.toString())
            }else{
                Toast.makeText(this,"Empty value",Toast.LENGTH_LONG).show()
            }
            etName.text.clear()
            etLocation.text.clear()
        }
        bSearch.setOnClickListener {
            // Check the Edit text is Empty
            if (etSName.text.isNotEmpty()){
                Search(etSName.text.toString())
            }else{

            }
            etSName.text.clear()
        }
    }
    //---------------------------------------------------------------------------------------------
    // fun for get data and search the name and show the location.
    private fun Search(Name: String) {
        api?.getData()?.enqueue(object : Callback<List<UserDetails>>{
            override fun onResponse(
                call: Call<List<UserDetails>>,
                response: Response<List<UserDetails>>,
            ) {
                for (i in response.body()!!){
                    if(Name == i.name){
                        tvLocation.text = i.location
                    }
                }
            }

            override fun onFailure(call: Call<List<UserDetails>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error $t",Toast.LENGTH_LONG).show()
            }

        })
    }
    //---------------------------------------------------------------------------------------------
    // fun to put in data.
    private fun AddData(Name: String, Location: String) {
        api?.putData(UserDetails(0,Name,Location))?.enqueue(object : Callback<UserDetails>{
            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                Toast.makeText(this@MainActivity,"Completed Add",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error $t",Toast.LENGTH_LONG).show()
            }

        })
    }
}