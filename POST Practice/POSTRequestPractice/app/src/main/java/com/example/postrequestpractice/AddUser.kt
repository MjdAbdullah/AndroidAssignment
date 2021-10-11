package com.example.postrequestpractice

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

class AddUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        val etName = findViewById<EditText>(R.id.etName)
        val etLocation = findViewById<EditText>(R.id.etLoction)
        val bView = findViewById<Button>(R.id.bView)
        val bAdd = findViewById<Button>(R.id.bSave)

        bView.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        bAdd.setOnClickListener {
            var data = Users.UserData(etName.text.toString(),etLocation.text.toString())
            val api = APIClient().getClient()?.create(APIInterface::class.java)
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("WAIT")
            progressDialog.show()
            api?.putUser(data)?.enqueue(object : Callback<Users.UserData>{
                override fun onResponse(
                    call: Call<Users.UserData>,
                    response: Response<Users.UserData>,
                ) {
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<Users.UserData>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(this@AddUser,"Error", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}