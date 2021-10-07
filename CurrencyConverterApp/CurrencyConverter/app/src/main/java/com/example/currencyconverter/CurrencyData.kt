package com.example.currencyconverter

import com.google.gson.annotations.SerializedName

class CurrencyData {
        @SerializedName("date")
        var date: String? = null
        @SerializedName("eur")
        var eur : Dat2? = null

        class Dat2 {
            @SerializedName("inr")
            var inr: String? = null

            @SerializedName("usd")
            var usd: String? = null

            @SerializedName("aud")
            var aud: String? = null

            @SerializedName("sar")
            var sar: String? = null

            @SerializedName("cny")
            var cny: String? = null

            @SerializedName("jpy")
            var jpy: String? = null
        }
}