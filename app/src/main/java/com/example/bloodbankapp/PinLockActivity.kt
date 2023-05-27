package com.example.bloodbankapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            onPinCorrect = { onPinMatched() },
            onPinIncorrect = { onPinIncorrect() },
            onPinCreated = { onPinCreated() }
        )
    }

    private fun onPinCreated() {
        Toast.makeText(this@PinLockActivity, "Pin created successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@PinLockActivity, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onPinMatched() {
        Toast.makeText(this@PinLockActivity, "Pin matched", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@PinLockActivity, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onPinIncorrect() {
        Toast.makeText(this@PinLockActivity, "Incorrect pin", Toast.LENGTH_SHORT).show()
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BloodBankAppTheme {
            PinLockUI()
        }
    }
}
