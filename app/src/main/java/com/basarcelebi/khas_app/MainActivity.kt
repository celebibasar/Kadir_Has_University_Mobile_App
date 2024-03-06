package com.basarcelebi.khas_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HowToReg
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basarcelebi.khas_app.screens.LoginViewModel
import com.basarcelebi.khas_app.screens.MainScreen
import com.basarcelebi.khas_app.screens.ProfileScreen
import com.basarcelebi.khas_app.screens.RegisterScreen
import com.basarcelebi.khas_app.screens.Screens
import com.basarcelebi.khas_app.screens.WeatherScreen
import com.basarcelebi.khas_app.ui.theme.GreenJC
import com.basarcelebi.khas_app.ui.theme.Khas_appTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
            Khas_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent))
                {
                    MyBottomAppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomAppBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = GreenJC,
                contentColor = Color.White
            ) {
                IconButton(onClick = {
                    navigationController.navigate(Screens.MainScreen.screen){
                        popUpTo(0)
                    }
                    selected.value = Icons.Default.Home
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.Gray
                    )
                }
                IconButton(onClick = {
                    navigationController.navigate(Screens.ProfileScreen.screen){
                        popUpTo(0)
                    }
                    selected.value = Icons.Default.Person
                }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = if (selected.value == Icons.Default.Person) Color.White else Color.Gray
                    )
                }

                Box(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center) {
                    FloatingActionButton(onClick = { navigationController.navigate(Screens.RegisterScreen.screen){
                        popUpTo(0)
                    }
                        selected.value = Icons.Default.HowToReg }) {
                        Icon(
                            imageVector = Icons.Default.HowToReg,
                            contentDescription = "Register",
                            tint = GreenJC
                        )
                        
                    }
                }
                IconButton(onClick = {
                    navigationController.navigate(Screens.WeatherScreen.screen){
                        popUpTo(0)
                    }
                    selected.value = Icons.Default.Cloud
                }) {
                    Icon(
                        imageVector = Icons.Default.Cloud,
                        contentDescription = "Weather",
                        tint = if (selected.value == Icons.Default.Cloud) Color.White else Color.Gray
                    )
                }
                IconButton(onClick = {
                    navigationController.navigate(Screens.RegisterScreen.screen){
                        popUpTo(0)
                    }
                    selected.value = Icons.Default.HowToReg
                }) {
                    Icon(
                        imageVector = Icons.Default.HowToReg,
                        contentDescription = "Register",
                        tint = if (selected.value == Icons.Default.HowToReg) Color.White else Color.Gray
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(navController = navigationController, startDestination = Screens.MainScreen.screen, modifier = Modifier.padding(paddingValues)) {
            composable(Screens.MainScreen.screen) {
                MainScreen(context = context)
            }
            composable(Screens.ProfileScreen.screen) {
                ProfileScreen()
            }
            composable(Screens.WeatherScreen.screen) {
                RegisterScreen()
            }
            composable(Screens.RegisterScreen.screen) {
                RegisterScreen()
            }
        }
    }
}



