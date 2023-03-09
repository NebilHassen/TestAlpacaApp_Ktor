package com.example.testalpacaapp_ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testalpacaapp_ktor.ui.TestAlpacaScreen
import com.example.testalpacaapp_ktor.ui.theme.TestAlpacaApp_KtorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAlpacaApp_KtorTheme {
                // A surface container using the 'background' color from the theme
                TestAlpacaScreen()


                }
            }
        }
    }



