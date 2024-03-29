package com.basarcelebi.khas_app.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.basarcelebi.khas_app.R
import com.basarcelebi.khas_app.ui.theme.googlesansbold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel? = null,navController: NavController = rememberNavController(),) {
    val openUrlLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .size(75.dp)
            .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .clickable {
                        val url = "https://www.khas.edu.tr/"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        openUrlLauncher.launch(intent)
                    }
                ,
                painter = painterResource(R.drawable.khas_logo),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Kadir Has University", fontSize = 20.sp,fontFamily = googlesansbold)
        Text(text = "Mobile Application", fontSize = 20.sp,fontFamily = googlesansbold)
        Spacer(modifier = Modifier.height(8.dp))
        if (isError){
            Text(
                text = loginUiState?.loginError ?: "unknown error",
                color = Color.Red,
            )
        }
        OutlinedTextField(
            value = loginUiState?.userName ?: "",
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            onValueChange = {loginViewModel?.onUserNameChange(it)},
            label = { Text("Student Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            isError = isError
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = loginUiState?.password ?: "",
            onValueChange = { loginViewModel?.onPasswordNameChange(it) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            singleLine = true,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            isError = isError
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                loginViewModel?.loginUser(context,navController) { isSuccessful ->
                    if (isSuccessful) {
                        navController.navigate(Screens.MainScreen.screen)
                    }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Giriş")
        }




    }

    if (loginUiState?.isLoading == true){
        CircularProgressIndicator()
    }

    LaunchedEffect(key1 = loginViewModel?.hasUser){
        if (loginViewModel?.hasUser == true){
            navController.navigate(Screens.MainScreen.screen)
        }
    }


}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen()

}