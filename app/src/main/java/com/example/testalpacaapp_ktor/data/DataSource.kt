package com.example.testalpacaapp_ktor.data

import android.net.http.HttpResponseCache
import com.example.testalpacaapp_ktor.ui.AlpacaVoteState
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken


class DataSource (val path: String ) {


    private val client = HttpClient () {
        install( ContentNegotiation ) {
            gson()
        }
    }



    suspend fun fetchAlpacaParties(dis: String) :List <TestAlpacaParty >{

        val alpacaParties: TestAlpacaParties= client.get(path).body()
        val Stemmer= fetchAlpacaVotes(dis)

        // countVotes(res.parties, votes) Måte å telle stemmer på

        return alpacaParties.parties
    }



    suspend fun fetchAlpacaVotes(dis: String) :List <Stemme>{

        val Stemmer: List<Stemme> = client.get(path).body()
        return Stemmer // her så lager vi en liste som er av typen Stemme
    }
















    /*

    suspend fun fetchAlpacaVotes() : List<Stemme> {
        val a : AlpacaVoteState= client.get(path).body()

        val r: ArrayList<Stemme>

        val Stemmer: StemmeListe= client.get(path).body()   // denne getter Valg resultat:
        var gson =  Gson()
        var b =  AlpacaVoteState (Stemmer.stemmeListe)

        var i : Int=0
        //while (Stemmer.hasNext()) {  } // denne går ikke Stemmer er vist ikke en lang liste med json objekter
        var  array = gson.fromJson(client.get(path).body(),  b )


        while (Stemmer.stemmeListe.hasNext())
        {
            r[0]
        }

        for ( Stemme in  Stemmer.stemmeListe ) {

            if (Stemme.id == "1"  ) {
                var i++

            }
        }


            Person person = gson.fromJson(element, Person.class);
            System.out.println(person)

        }

        /*
        Gson gson = new Gson();
        val StemmeListee: StemmeListe= gson.fromJson(Stemmer,Stemme.class);
        */

        return Stemmer.stemmeListe // Her så returnerer vi et objekt av typen StemmeListe
    }

     */


    /*

    suspend fun StemmeTeller () : Int {

        val Stemmer: <Stemme> = client.get("https://in2000-proxy.ifi.uio.no/alpacaapi/district1").body()

        val ant= Stemmer.stemmeListe.size
        return ant
    }

     */





}