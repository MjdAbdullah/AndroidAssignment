package com.example.simplegetrequest

import com.google.gson.annotations.SerializedName

data class StudentDataItem(
    @SerializedName("name")
    val name: String
)