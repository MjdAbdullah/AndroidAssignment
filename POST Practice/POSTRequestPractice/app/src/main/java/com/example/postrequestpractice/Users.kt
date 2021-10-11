package com.example.postrequestpractice

import com.google.gson.annotations.SerializedName

class Users {

    var UserList : List<UserData>? = null

    class UserData {
        @SerializedName("name")
        var name : String? = null

        @SerializedName("location")
        var location : String? = null

        constructor(name: String, Location :String){
            this.name = name
            this.location = Location
        }
    }
}