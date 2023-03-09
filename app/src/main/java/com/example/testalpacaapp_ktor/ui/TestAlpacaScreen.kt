package com.example.testalpacaapp_ktor.ui
import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.testalpacaapp_ktor.data.TestAlpacaParty


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestAlpacaScreen(
    testAlpacaViewModel: TestAlpacaViewModel = viewModel(),
    AlpacaVoteStateObj: TestAlpacaViewModel = viewModel() ) {

    val testAlpacaUIState by testAlpacaViewModel.testAlpacaUiState.collectAsState()
    val alpacaVoteStateObj by AlpacaVoteStateObj.testAlpacaVote.collectAsState()



    Scaffold(
        topBar = { TopAppBar(title = {  DropDownValgMeny() }) },
    )
    {

        LazyColumn (modifier = Modifier.padding(PaddingValues())) {

            items(testAlpacaUIState.parties) { testalpacaparti ->
                TestAlpacaCard(testalpacaparti = testalpacaparti)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TestAlpacaCard (testalpacaparti: TestAlpacaParty) {



    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        Spacer (modifier= Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .wrapContentHeight(Alignment.Top)
            .height(30.dp)
            .background(
                color = Color(
                    parseColor(testalpacaparti.color)
                )
            )
        )

        Text(
            text= testalpacaparti.name, modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),fontWeight = FontWeight.Bold,
        )

        AsyncImage(
            model = testalpacaparti.img,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier= Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .size(120.dp)
                .clip(CircleShape)
        )

        Text(
            text= "Leader: " + testalpacaparti.leader,

            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally))


        //Dette tekstfeltet er for Stemmer:
        Text(
            text = testalpacaparti.votes.toString()  ,fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)// tror filmmMaxWidth kommer til å skape problemer senere for DropDownMeny
        )

    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownValgMeny() { // her Velger man distrikt 1-3


    var expanded by remember { mutableStateOf(false) }
    var distrikt = listOf("Distrikt 1","Distrikt 2","Distrikt 3")
    var valgtdistrikt by remember { mutableStateOf(distrikt[0]) }


    ExposedDropdownMenuBox(
        modifier= Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(7.dp),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = valgtdistrikt,
            onValueChange = {},
            label = { Text("Velg distrikt") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            distrikt.forEach { etdistrikt ->
                DropdownMenuItem(
                    modifier= Modifier.wrapContentWidth(Alignment.CenterHorizontally),
                    text = { Text(etdistrikt) },
                    onClick = {
                        valgtdistrikt = etdistrikt
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }

}

/*

class Menyvalg  {


    var valg : Array<String> = arrayOf("https://in2000-proxy.ifi.uio.no/alpacaapi/district1","https://in2000-proxy.ifi.uio.no/alpacaapi/district2","https://in2000-proxy.ifi.uio.no/alpacaapi/district3")


    fun Menyvalg ( v : Int ) : String {


        return valg[v]
    }
}


 */






