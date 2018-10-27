package com.j2rvaservud.tluhomeworks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Group (
        @PrimaryKey
        var id: Int,
        @SerializedName("code")
        var code: String = "",
        @SerializedName("name")
        var name: String? = ""
)