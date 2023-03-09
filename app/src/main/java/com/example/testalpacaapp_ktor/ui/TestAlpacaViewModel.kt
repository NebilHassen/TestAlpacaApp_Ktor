package com.example.testalpacaapp_ktor.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testalpacaapp_ktor.data.DataSource
import com.example.testalpacaapp_ktor.data.Stemme
import com.example.testalpacaapp_ktor.data.StemmeListe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class TestAlpacaViewModel: ViewModel() {

    //  DEtte er path for Partier-API:
    private val dataSource= DataSource("https://in2000-proxy.ifi.uio.no/alpacaapi/alpacaparties")


    //Prøv å få kontakt med Dropdownmenyvalg fra AlpacaScreen

    //  DEtte er path for stemmer-API'et:
    private val dataSourceVotes =DataSource("https://in2000-proxy.ifi.uio.no/alpacaapi/district1")










    private val sssss : List<Stemme> = listOf()

    // Disse lager og lagrer tomme Mutable state objekter av data innhentet
    private val _TestAlpacaUIState= MutableStateFlow (TestAlpacaUiState(parties = listOf()))
    private val _TestAlpacaUIStemme= MutableStateFlow (sssss) // endres fra stemmeListe= listOf - en tom liste



    //Endrer på denne: Nå i dag 18.44
    val testAlpacaUiState: StateFlow<TestAlpacaUiState> = _TestAlpacaUIState.asStateFlow()
    val testAlpacaVote: StateFlow<List<Stemme>> = _TestAlpacaUIStemme.asStateFlow()


    //Init:
    // Kjører begge funksjonene(at API'et hentes fra start) fra første øyeblikk appen igangsettes:
    //Både loadUsers() og loadStemmer() henter innholdet/objektene sine fra Http-klienten
    // angitt i DataSource-filen: og begge kjøres i viewModelScope.launch som
    //gjør at loadUsers() og loadStemmer() kjøres som suspended Coroutines

    init {
        loadUsers("Distrikt 1")
        loadStemmer("Distrikt 1")
    }

    private fun loadUsers(dis: String ) {
        viewModelScope.launch (Dispatchers.IO) {
            val alpacapartier = dataSource.fetchAlpacaParties("Distrikt 1")
            _TestAlpacaUIState.value= TestAlpacaUiState( parties = alpacapartier)
        }
    }

//loadStemmer:  Før så sto det: val stemmer = dataSourceVotes.fetchAlpacaVotes() Altså vi type anga ikke til : StemmeListe



    //DEnne setter stemmer/dataen hentet fra distrikt API'et til  et objekt av typen AlpacaVoteState
    // Denne funksjonen fyller/setter verdien til objektet _TestAlpacaUIStemme som innholdet fra Http-klienten vår har requested
    private fun loadStemmer(dis: String) {
        viewModelScope.launch (Dispatchers.IO) {
            val parties = dataSource.fetchAlpacaParties(dis)


            val stemmer = dataSourceVotes.fetchAlpacaVotes(dis) // Dette er stemmene: hentet ved hjelp av HTTP-klient'en angitt i DataSource

            Log.d("ViewModel", stemmer.toString())
            _TestAlpacaUIStemme.value= stemmer

            for (Stemme in stemmer) {
                if (Stemme.id == "1") { parties.get(0).votes++}
                if (Stemme.id == "2") { parties.get(1).votes++}
                if (Stemme.id == "3") { parties.get(2).votes++}
                if (Stemme.id == "4") { parties.get(3).votes++}
            }

        }
    }




}