package com.example.simplepostrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    var CustomrList = arrayListOf<CustomPersonListItem>()
    val api = APIClient().getClient()?.create(APIInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)

        // call fun
        DisplayData()

        val bAdd = findViewById<Button>(R.id.button)
        val etName = findViewById<EditText>(R.id.etName)

        // Activated the button
        bAdd.setOnClickListener {
            //Check if the edit text not empty
            if (etName.text.isNotEmpty()){
                AddData(etName.text.toString())     //call fun
            }else{
                Toast.makeText(this,"NULL VALUE",Toast.LENGTH_LONG).show()
            }
        }
    }
    //-----------------------------------------------------------------------------------------------
    // Fun for add to the data
    private fun AddData(UserInput: String) {
        api?.putData(CustomPersonListItem(UserInput,0))?.enqueue(object : Callback<CustomPersonListItem>{
            override fun onResponse(
                call: Call<CustomPersonListItem>,
                response: Response<CustomPersonListItem>,
            ) {
                Toast.makeText(this@MainActivity,"Add Completed",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<CustomPersonListItem>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Add Filed",Toast.LENGTH_LONG).show()
            }

        })
    }
    //-----------------------------------------------------------------------------------------------
    //fun for get from api and show in data rv
    private fun DisplayData(){
        api?.getData()?.enqueue(object : Callback<List<CustomPersonListItem>> {
            override fun onResponse(
                call: Call<List<CustomPersonListItem>>,
                response: Response<List<CustomPersonListItem>>,
            ) {
                for (i in response.body()!!){
                    CustomrList.add(CustomPersonListItem(i.name,i.pk))
                }
                rvMain.adapter = RecyclerViewAdapter(this@MainActivity,CustomrList)
                rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<List<CustomPersonListItem>>, t: Throwable) {
                println("Error $t")
                Toast.makeText(this@MainActivity,"Error $t",Toast.LENGTH_LONG).show()
            }

        })
    }
}