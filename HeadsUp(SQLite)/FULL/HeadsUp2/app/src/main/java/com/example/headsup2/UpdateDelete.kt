package com.example.headsup2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UpdateDelete : AppCompatActivity() {

    companion object{
        lateinit var DB : DBHlpr
    }
    var CelebrityArray = ArrayList<CelebrityModel>()
    lateinit var rvCelebrity : RecyclerView
    var RV = RVAdapter(this,CelebrityArray)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        DB = DBHlpr(applicationContext)
        rvCelebrity =findViewById(R.id.rvCelebrity)
        getArray()
        rvCelebrity.adapter = RV
        rvCelebrity.layoutManager = LinearLayoutManager(this)
    }

    private fun getArray() {
        CelebrityArray = DB.getData()
        RV.Update(CelebrityArray)
    }

    fun delete(SelectCelebrity: CelebrityModel){
        DB.deleteData(SelectCelebrity)
        getArray()
    }

    fun ShowAlert(SelectCelebrity: CelebrityModel){
        val alert =  AlertDialog.Builder(this)
        val L = LinearLayout(this)
        val etNameAlert = EditText(this)
        etNameAlert.setText(SelectCelebrity.Name)
        val etT1Alert = EditText(this)
        etT1Alert.setText(SelectCelebrity.Taboo1)
        val etT2Alert = EditText(this)
        etT2Alert.setText(SelectCelebrity.Taboo2)
        val etT3Alert = EditText(this)
        etT3Alert.setText(SelectCelebrity.Taboo3)

        L.orientation = LinearLayout.VERTICAL
        L.apply {
            addView(etNameAlert)
            addView(etT1Alert)
            addView(etT2Alert)
            addView(etT3Alert)
        }

        alert.setTitle("Edit CelebrityModel")
            .setView(L)
            .setPositiveButton("UPDATE"){
                    dialog,which ->
                if(etNameAlert.text.isNotEmpty() && etT1Alert.text.isNotEmpty() && etT2Alert.text.isNotEmpty()
                    && etT3Alert.text.isNotEmpty()){
                    val ID = SelectCelebrity.Id
                    val Name = etNameAlert.text.toString()
                    val T1 = etT1Alert.text.toString()
                    val T2 = etT2Alert.text.toString()
                    val T3 = etT3Alert.text.toString()
                    DB.updateDate(CelebrityModel(ID,Name,T1,T2,T3))
                    getArray()
                }else{
                    Toast.makeText(this,"Please enter values",Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()
    }


}