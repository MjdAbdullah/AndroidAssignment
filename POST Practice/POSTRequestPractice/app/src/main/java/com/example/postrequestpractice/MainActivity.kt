package com.example.postrequestpractice

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bAdd = findViewById<Button>(R.id.bAdduser)
        bAdd.setOnClickListener {
            val intent = Intent(this,AddUser::class.java)
            startActivity(intent)
        }
        val tvShow = findViewById<TextView>(R.id.tvUser)
        val api = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("WAIT")
        progressDialog.show()
            api?.getUserList()?.enqueue(object :Callback<List<Users.UserData>> {
                override fun onResponse(
                    call: Call<List<Users.UserData>>,
                    response: Response<List<Users.UserData>>,
                ) {
                    var Data : String? = "";
                    for(User in response.body()!!){
                        Data = "$Data${User.name}\n${User.location}\n\n"
                    }
                    progressDialog.dismiss()
                    tvShow.text = Data
                }

                override fun onFailure(call: Call<List<Users.UserData>>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_LONG).show()
                }
            })
    }
}