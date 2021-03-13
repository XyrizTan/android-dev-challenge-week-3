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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                LoginScreen()
            }
        }
    }
}

// Start building your app here!
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.login_title))

            // TODO: Error validation
            val emailTextValue = remember { mutableStateOf("") }
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = stringResource(R.string.login_email_textfield_hint)) },
                value = emailTextValue.value,
                onValueChange = { emailTextValue.value = it })

            // TODO: Error validation
            val passwordTextValue = remember { mutableStateOf("") }
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = stringResource(R.string.login_password_textfield_hint)) },
                value = passwordTextValue.value,
                onValueChange = { emailTextValue.value = it },
            )

            // TODO: Link movement method for Terms of Use and Privacy Policy
            Text(text = stringResource(R.string.login_tos))

            // TODO: Button styling and sizing
            Button(onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            }) {
                Text(stringResource(R.string.login_button_text))
            }
        }

    }
}