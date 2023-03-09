package com.example.testalpacaapp_ktor.data

import com.google.gson.annotations.SerializedName


data class TestAlpacaParty (

    @SerializedName("id"     ) var id     : String,
    @SerializedName("name"   ) var name   : String,
    @SerializedName("leader" ) var leader : String,
    @SerializedName("img"    ) var img    : String,
    @SerializedName("color"  ) var color  : String,
    var votes : Int = 0

)