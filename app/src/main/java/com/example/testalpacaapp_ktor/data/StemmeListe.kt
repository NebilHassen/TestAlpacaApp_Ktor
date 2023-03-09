package com.example.testalpacaapp_ktor.data

import com.google.gson.annotations.SerializedName

data class StemmeListe (
    @SerializedName("Stemmeliste" ) var stemmeListe : ArrayList<Stemme> = arrayListOf()
)