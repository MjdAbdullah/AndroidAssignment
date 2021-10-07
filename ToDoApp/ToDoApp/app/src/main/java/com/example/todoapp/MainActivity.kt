package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rvMain : RecyclerView
    var arr = arrayListOf<Item>()
    lateinit var clMain : ConstraintLayout
    var numItem = 0         //to count the number of item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        clMain = findViewById(R.id.clMain)
        val abAdd = findViewById<FloatingActionButton>(R.id.abAdd)

        rvMain.adapter = RecyclerViewAdapter(this, arr)
        rvMain.layoutManager = LinearLayoutManager(this)

        abAdd.setOnClickListener{
            Add()
        }
    }
    //-------------------------------------------------------------------------------------------
    // Add new item in To Do
    fun Add(){
        val alert = AlertDialog.Builder(this)
        val L = LinearLayout(this)
        val etItem = EditText(this)
        L.orientation = LinearLayout.VERTICAL
        L.addView(etItem)

        alert.setTitle("New Item")
            .setView(L)
            .setPositiveButton("ADD"){
                    dialog,which -> arr.add(Item(etItem.text.toString() , false))
            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()
        rvMain.adapter?.notifyDataSetChanged()
    }
    //-------------------------------------------------------------------------------------------
    // for menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Delete -> {
                numItem = 0
                for (i in 0..arr.size-1){
                    if (arr[i].Check == true){
                        arr.removeAt(i)
                        numItem += 1
                    }
                }
                rvMain.adapter?.notifyDataSetChanged()
            }
        }
        // Show for user the number of Item that Deleted
        Toast.makeText(this, "$numItem Item Deleted", Toast.LENGTH_SHORT).show()
        numItem = 0
        return super.onOptionsItemSelected(item)
    }
}
