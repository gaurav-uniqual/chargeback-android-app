package com.chargeback.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.chargeback.app.screens.SubscriptionScreen
import com.chargeback.app.ui.theme.ChargebackAndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChargebackAndroidAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    SubscriptionScreen()
                }
            }
        }
    }
}