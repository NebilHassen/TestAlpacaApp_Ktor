package com.example.testalpacaapp_ktor.data

import com.google.gson.annotations.SerializedName


data class TestAlpacaParties (

    @SerializedName("parties" ) var parties : ArrayList<TestAlpacaParty> = arrayListOf()

)