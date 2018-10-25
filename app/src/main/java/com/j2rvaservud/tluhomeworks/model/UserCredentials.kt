package com.j2rvaservud.tluhomeworks.model

import com.google.gson.annotations.SerializedName

data class UserCredentials (

    @SerializedName("username")
    var username: String = "",

    @SerializedName("password")
    var password: String = ""

)