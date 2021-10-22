package com.example.news

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @get:GET("news/.rss")
    val feed: Call<Feed?>?

    companion object {
        const val BASE_URL = "https://www.reddit.com/r/"
    }
}