package com.example.recipeapp

import com.example.getandpostrequests.CustomPersonListItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/custom-people/")
    fun getData(): Call<List<CustomPersonListItem>>

    @Headers("Content-Type: application/json")
    @POST("/custom-people/")
    fun putData(@Body userData: CustomPersonListItem): Call<CustomPersonListItem>
}