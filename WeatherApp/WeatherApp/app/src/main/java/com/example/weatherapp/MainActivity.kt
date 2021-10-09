package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val Kay = "6aef100c23f05d32f0544a56aa479a01"
    var City = "10001"      // Start city
    var Swap = "C"          // For sawp the temp

    //Variable for save the temperature
    var temp : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bRef = findViewById<ImageButton>(R.id.bRef)
        val bSwapTemp = findViewById<ImageButton>(R.id.bSwapTemp)
        val bNewZip = findViewById<ImageButton>(R.id.bNewZip)

        // To Refresh Data
        bRef.setOnClickListener {
            Swap = "C"
            onCallAPI()
        }

        // To Enter New Zip code
        bNewZip.setOnClickListener { EnterNewZip() }

        // Change the temperature
        bSwapTemp.setOnClickListener {
            if(Swap.equals("C")){
                temp = (( temp * 1.8) + 32).toInt()
                findViewById<TextView>(R.id.tvTemNumber).text = "$temp°F"
                Swap = "F"
            }
            else{
                temp = ((temp - 32) / 1.8).toInt()
                findViewById<TextView>(R.id.tvTemNumber).text = "$temp°C"
                Swap = "C"
            }
        }
        onCallAPI()
    }
    //----------------------------------------------------------------------------------------------
    // Show Alert for user to ask him enter the new Zip code or cancel
    private fun EnterNewZip() {
        val layout = LinearLayout(this)
        val etZip = EditText(this)
        etZip.inputType = Int.MAX_VALUE
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(etZip)
        val alert =  AlertDialog.Builder(this)
        alert.apply {
            setView(layout)
            setMessage("Enter new Zip code:")
            setTitle("Weather App")
            setPositiveButton("OK"){
                    dialog,which -> City = etZip.text.toString()
                onCallAPI()
                dialog.cancel()

            }
            setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            show()
        }
    }
    //----------------------------------------------------------------------------------------------
    // Get API
    fun onCallAPI(){
        CoroutineScope(Dispatchers.IO).launch {
            var call =""
            try {
                call = URL("https://api.openweathermap.org/data/2.5/weather?zip=$City&units=metric&appid=$Kay")
                    .readText(Charsets.UTF_8)
                getData(call)
            } catch (e: Exception) {
                println("Error $e")
                call = ""
            }
            if(call.isNotEmpty()){
                getData(call)
            }else{
                ShowAlert()
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    // to show Alert error for user if he enter wrong Zip Code and back it to the start city -> "10001"
    suspend fun ShowAlert() {
        withContext(Dispatchers.Main) {
            val alert1 = AlertDialog.Builder(this@MainActivity)
            alert1.apply {
                setMessage("Something wrong")
                setTitle("WRONG")
                setPositiveButton("RETURN") { dialog, which ->
                    City = "10001"
                    onCallAPI()
                    dialog.cancel()
                }
                show()
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    // Read data and Show it for user
    suspend fun getData(Data: String) {
          withContext(Dispatchers.Main) {
              try {
                  // Read Database
                  val j = JSONObject(Data)
                  val main = j.getJSONObject("main")
                  val sys = j.getJSONObject("sys")
                  val wind = j.getJSONObject("wind")
                  val weather = j.getJSONArray("weather").getJSONObject(0)
                  val data = j.getLong("dt")
                  val Updata =
                      "Updated at: " + SimpleDateFormat("dd/MM/yy hh:mm a", Locale.ENGLISH)
                          .format(Date(data * 1000))
                  temp = main.getInt("temp")
                  val low = main.getString("temp_min")
                  val high = main.getString("temp_max")
                  val pressure = main.getString("pressure")
                  val humidity = main.getString("humidity")
                  val sunrise = sys.getLong("sunrise")
                  val sunset = sys.getLong("sunset")
                  val windspeed = wind.getString("speed")
                  val des = weather.getString("description")
                  val cityName = j.getString("name")
                  val countryName = sys.getString("country")

                  // Show the Data
                  findViewById<TextView>(R.id.tvCityName).text = "$cityName, $countryName"
                  findViewById<TextView>(R.id.tvData).text = "${Updata.capitalize()}"
                  findViewById<TextView>(R.id.tvState).text = des
                  findViewById<TextView>(R.id.tvTemNumber).text = "$temp°C"
                  findViewById<TextView>(R.id.tvLow).text = "Low: $low°C"
                  findViewById<TextView>(R.id.tvHigh).text ="High: $high°C"
                  findViewById<TextView>(R.id.tvSRH).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                      .format(Date(sunrise*1000))   // SRH -> sunrise hour
                  findViewById<TextView>(R.id.tvSSH).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                      .format(Date(sunset*1000)) // SSH -> sunset hour
                  findViewById<TextView>(R.id.tvWind).text = windspeed
                  findViewById<TextView>(R.id.tvPressure).text = pressure
                  findViewById<TextView>(R.id.tvHumidity).text = humidity

                  Swap = "C"

              } catch (e: Exception) {
                  println("---- In Read: $e -----")
              }
          }
    }
}//main