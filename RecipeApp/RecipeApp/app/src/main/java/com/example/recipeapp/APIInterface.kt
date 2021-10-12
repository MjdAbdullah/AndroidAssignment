package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    //@Headers("Content-Type: application/json")
    @GET("/recipes/")
    fun getData(): Call<List<RecipesDetails.Datum>>

    @Headers("Content-Type: application/json")
   @POST("/recipes/")
   fun putData(@Body userData: RecipesDetails.Datum): Call<RecipesDetails>
}