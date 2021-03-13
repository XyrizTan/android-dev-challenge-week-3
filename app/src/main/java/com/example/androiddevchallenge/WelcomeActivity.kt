package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.androiddevchallenge.ui.theme.MyTheme

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                WelcomeScreen()
            }
        }
    }
}

// Start building your app here!
@Composable
fun WelcomeScreen() {
    val context = LocalContext.current
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO: Load background image assets
            Text(text = stringResource(R.string.app_name))
            Text(text = stringResource(R.string.welcome_subtitle))

            // TODO: Button styling and sizing
            Button(onClick = {

            }) {
                Text(stringResource(R.string.welcome_create_account_button_text))
            }

            // TODO: Button styling and sizing
            Button(onClick = {
                context.startActivity(Intent(context, LoginActivity::class.java))
            }) {
                Text(stringResource(R.string.login_button_text))
            }
        }

    }
}