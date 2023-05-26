package com.example.bloodbankapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bloodbankapp.ui.theme.BloodBankAppTheme
import xyz.teamgravity.pin_lock_compose.PinLock
import xyz.teamgravity.pin_lock_compose.PinManager

class PinLockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodBankAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PinLockUI()
                }
            }
        }
    }

    @Composable
    private fun PinLockUI() {
        PinManager.initialize(this@PinLockActivity)
        PinLock(
            title = { pinExists -> Text(text = if (pinExists) "Enter your pin" else "Create pin") },
            color = MaterialTheme.colorScheme.surface,
            onPinCorrect = { finish() },
            onPinIncorrect = { },
            onPinCreated = {finish()}
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BloodBankAppTheme {
            PinLockUI()
        }
    }
}
