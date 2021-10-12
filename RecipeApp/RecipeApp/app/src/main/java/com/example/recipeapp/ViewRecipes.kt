package com.example.recipeapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewRecipes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipes)

        val bAdd = findViewById<Button>(R.id.bAdd)
        bAdd.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val rvMain = findViewById<RecyclerView>(R.id.rvMain)
        val api = APIClient().getClient()?.create(APIInterface::class.java)

        val progressDialog = ProgressDialog(this@ViewRecipes)
        progressDialog.apply {
            setMessage("WAIT")
            show()
        }
        if(api != null){
            api!!.getData()?.enqueue(object : Callback<List<RecipesDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<RecipesDetails.Datum>>,
                    response: Response<List<RecipesDetails.Datum>>,
                ) {
                    var Line = arrayListOf<String?>()
                    for (recope in response.body()!!){
                        Line.add("${recope.title}\n${recope.author}\n${recope.ingredients}\n${recope.instructions}")
                    }
                    rvMain.adapter = RecyclerViewAdapter(this@ViewRecipes,Line)
                    rvMain.layoutManager = LinearLayoutManager(this@ViewRecipes)
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<List<RecipesDetails.Datum>>, t: Throwable) {
                    progressDialog.dismiss()
                    println(t.message)
                    Toast.makeText(this@ViewRecipes,"Error ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}