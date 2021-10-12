package com.example.recipeapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etAuthor = findViewById<EditText>(R.id.etAuthor)
        val etIngredients = findViewById<EditText>(R.id.etIngredients)
        val etInstructions = findViewById<EditText>(R.id.etInstructions)

        val bView = findViewById<Button>(R.id.bView)
        bView.setOnClickListener {
            val intent = Intent(this,ViewRecipes::class.java)
            startActivity(intent)
        }
        val bSave = findViewById<Button>(R.id.bSave)
        bSave.setOnClickListener {
            val UserInput =RecipesDetails.Datum(etTitle.text.toString(),etAuthor.text.toString()
                                 ,etIngredients.text.toString(),etInstructions.text.toString())
            val api = APIClient().getClient()?.create(APIInterface::class.java)
            val progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.apply {
                setMessage("WAIT")
                show()
            }
                api?.putData(UserInput)?.enqueue(object : Callback<RecipesDetails>{
                    override fun onResponse(
                        call: Call<RecipesDetails>,
                        response: Response<RecipesDetails>,
                    ) {
                        progressDialog.dismiss()
                        etAuthor.text.clear()
                        etIngredients.text.clear()
                        etInstructions.text.clear()
                        etTitle.text.clear()
                        Toast.makeText(this@MainActivity,"Completed", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<RecipesDetails>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}