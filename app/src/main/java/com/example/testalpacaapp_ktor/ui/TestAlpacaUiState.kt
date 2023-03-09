package com.example.testalpacaapp_ktor.ui

import com.example.testalpacaapp_ktor.data.Stemme
import com.example.testalpacaapp_ktor.data.TestAlpacaParty


data class TestAlpacaUiState(
    val parties: List<TestAlpacaParty> //, val valgtdistrikt : List<Stemme>
)
