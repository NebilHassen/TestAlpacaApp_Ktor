package com.example.testalpacaapp_ktor.ui

import com.example.testalpacaapp_ktor.data.Stemme
import com.example.testalpacaapp_ktor.data.TestAlpacaParty
import com.google.gson.annotations.SerializedName

data class AlpacaVoteState (
    val stemmeListe : List<Stemme>


)