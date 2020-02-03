package com.example.sportzintractivetask.model


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("Position")
    val position: String?,
    @SerializedName("Name_Full")
    val nameFull: String?,
    @SerializedName("Iskeeper")
    val iskeeper: Boolean?,
    @SerializedName("Iscaptain")
    val iscaptain: Boolean?
)