package com.example.currencyconverter

import android.app.TaskStackBuilder.create
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ran.am.retrofitkotlin.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI.create

class MainActivity : AppCompatActivity() {
    var num = 0         // for select object in spinner
    var Ruselt = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvRuselt = findViewById<TextView>(R.id.tvRuselt)
        val tvData = findViewById<TextView>(R.id.tvData)
        var bConvert = findViewById<Button>(R.id.bConvert)
        var Sp = findViewById<Spinner>(R.id.spinner)
        var etNumber = findViewById<EditText>(R.id.editTextNumber)

        val list = arrayListOf("inr","usd","aud","sar","cny","jpy")
        Sp.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)

        // Item select in spinner
        Sp.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    num = position
                }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        var arr : CurrencyData.Dat2? = null
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<CurrencyData?>? = apiInterface!!.doGetListResources()
        call?.enqueue(object : Callback<CurrencyData?> {
            override fun onResponse(call: Call<CurrencyData?>, response: Response<CurrencyData?>) {
                Toast.makeText(this@MainActivity, "Connected",Toast.LENGTH_LONG).show()
                val resource: CurrencyData? = response.body()
                tvData.text = resource?.date.toString()
                arr = resource?.eur
            }

            override fun onFailure(call: Call<CurrencyData?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error",Toast.LENGTH_LONG).show()
            }

        })
        bConvert.setOnClickListener {
            var temp = etNumber.text.toString()
            var UserInput = temp.toDouble()
            when(num){
                0 -> Calculator(UserInput, arr?.inr?.toDouble())
                1 -> Calculator(UserInput, arr?.usd?.toDouble())
                2 -> Calculator(UserInput, arr?.aud?.toDouble())
                3 -> Calculator(UserInput, arr?.sar?.toDouble())
                4 -> Calculator(UserInput, arr?.cny?.toDouble())
                5 -> Calculator(UserInput, arr?.jpy?.toDouble())
                else -> Ruselt = 0.0
            }
            tvRuselt.text = "Ruselt   $Ruselt"
            etNumber.text.clear()
        }

    }
    fun Calculator(UserInput: Double, num1: Double?){
        if(num1 != null){
            Ruselt = UserInput * num1
        }else{
            Ruselt = 0.0
        }
    }
}