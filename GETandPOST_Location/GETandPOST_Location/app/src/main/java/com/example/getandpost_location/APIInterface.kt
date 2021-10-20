package com.example.recipeapp

import com.example.getandpost_location.UserDetails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getData(): Call<List<UserDetails>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun putData(@Body userData: UserDetails): Call<UserDetails>
}