package com.example.notefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    val TAG = "MainActivity"
    val db = FirebaseFirestore.getInstance()
    var arr = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bAdd = findViewById<Button>(R.id.bAdd)
        val etNote = findViewById<EditText>(R.id.etNote)
        rvMain = findViewById(R.id.rvMain)

        bAdd.setOnClickListener {
            if (etNote.text.isNotEmpty()){
                val note = hashMapOf(
                    ("note" to etNote.text.toString())
                )
                etNote.text.clear()
                db.collection("notes")
                    .add(note)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }

                Toast.makeText(this,"Save it", Toast.LENGTH_SHORT).show()
                etNote.text.clear()
                etNote.clearFocus()
                getData()
            }
        }
        getData()
        rvMain.adapter = RVAdapter(arr, this)
        rvMain.layoutManager = LinearLayoutManager(this)

    }

    fun getData(){
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                arr.clear()
                for (document in result) {
                    var s = document.data.values.toString()
                    Log.d(TAG, "${document.id} => ${document.data} ${s}")
                    arr.add(s)
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error read document", e)
            }

        rvMain.adapter?.notifyDataSetChanged()
    }

}