package com.example.top10app

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @get: GET("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
    val feed: Call<Feed?>?

    @get: GET("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=100/xml")
    val feed100: Call<Feed?>?
}