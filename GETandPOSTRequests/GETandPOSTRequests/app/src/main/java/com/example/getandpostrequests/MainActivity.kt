package com.example.getandpostrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.APIClient
import com.example.recipeapp.APIInterface
import com.example.recipeapp.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val api = APIClient().getClient()?.create(APIInterface::class.java)
    var CustomList = arrayListOf<CustomPersonListItem>()
    lateinit var rvMain : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bAdd = findViewById<Button>(R.id.bAdd)
        val bView = findViewById<Button>(R.id.bView)
        val etName =findViewById<EditText>(R.id.etName)
        rvMain = findViewById(R.id.rvMain)
        bAdd.setOnClickListener {
            //Chek if the Edit Text is empty
            if (etName.text.isNotEmpty()){
                addData(etName.text.toString())
            }else{
                Toast.makeText(this@MainActivity,"Empty value",Toast.LENGTH_LONG).show()
            }
        }
        bView.setOnClickListener {
            ShowData()
        }
    }

    private fun ShowData() {
        api?.getData()?.enqueue(object : Callback<List<CustomPersonListItem>>{
            override fun onResponse(
                call: Call<List<CustomPersonListItem>>,
                response: Response<List<CustomPersonListItem>>,
            ) {
                for (i in response.body()!!){
                    CustomList.add(CustomPersonListItem(i.name,i.pk))
                }
                rvMain.adapter = RecyclerViewAdapter(this@MainActivity, CustomList)
                rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<List<CustomPersonListItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error $t",Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun addData(UserInput: String) {
        api?.putData(CustomPersonListItem(UserInput,0))?.enqueue(object : Callback<CustomPersonListItem>{
            override fun onResponse(
                call: Call<CustomPersonListItem>,
                response: Response<CustomPersonListItem>,
            ) {
                Toast.makeText(this@MainActivity,"Add Completed",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<CustomPersonListItem>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error $t",Toast.LENGTH_LONG).show()
            }

        })
    }
}