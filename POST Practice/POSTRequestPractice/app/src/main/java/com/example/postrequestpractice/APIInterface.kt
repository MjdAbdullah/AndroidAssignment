package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @GET("/test/")
    fun getUserList(): Call<List<Users.UserData>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun putUser(@Body userData: Users.UserData): Call<Users.UserData>
}