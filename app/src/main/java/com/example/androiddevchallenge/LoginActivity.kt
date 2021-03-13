package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.gray
import com.example.androiddevchallenge.ui.theme.pink900
import com.example.androiddevchallenge.ui.theme.white

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
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(R.string.login_title), style = MaterialTheme.typography.h1)

            val emailTextValue = remember { mutableStateOf("") }
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = stringResource(R.string.login_email_textfield_hint)) },
                value = emailTextValue.value,
                onValueChange = { emailTextValue.value = it },
                textStyle = MaterialTheme.typography.body1,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                        shape = MaterialTheme.shapes.small
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            val passwordTextValue = remember { mutableStateOf("") }
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = stringResource(R.string.login_password_textfield_hint)) },
                value = passwordTextValue.value,
                onValueChange = { emailTextValue.value = it },
                textStyle = MaterialTheme.typography.body1,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                        shape = MaterialTheme.shapes.small
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            // TODO: Link movement method for Terms of Use and Privacy Policy
            Text(
                text = stringResource(R.string.login_tos),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    stringResource(R.string.login_button_text),
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }

    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginLightPreview() {
    MyTheme {
        LoginScreen()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginDarkPreview() {
    MyTheme(darkTheme = true) {
        LoginScreen()
    }
}
