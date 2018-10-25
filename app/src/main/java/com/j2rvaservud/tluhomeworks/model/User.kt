package com.j2rvaservud.tluhomeworks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    var id: Int,
    @SerializedName("username")
    var username: String = "",
    @SerializedName("email")
    var email: String? = ""
    )