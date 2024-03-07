package com.basarcelebi.khas_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basarcelebi.khas_app.repositories.AuthRepository
import com.basarcelebi.khas_app.screens.LoginScreen
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
    val repository: AuthRepository = AuthRepository()
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
                    if (repository.hasUser())
                    {
                        navigationController.navigate(Screens.MainScreen.screen){
                            popUpTo(0)
                        }

                    }else
                    {
                        navigationController.navigate(Screens.LoginScreen.screen){
                            popUpTo(0)
                        }

                    }

                    selected.value = Icons.Default.Home
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                IconButton(onClick = {
                    if (repository.hasUser())
                    {
                        navigationController.navigate(Screens.LoginScreen.screen){
                            popUpTo(0)
                        }

                    }else
                    {
                        navigationController.navigate(Screens.LoginScreen.screen){
                            popUpTo(0)
                        }

                    }

                    selected.value = Icons.Default.Login
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Default.Login,
                        contentDescription = "Login",
                        tint = if (selected.value == Icons.Default.Login) Color.White else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Box(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                    contentAlignment = androidx.compose.ui.Alignment.Center) {
                    FloatingActionButton(onClick = {
                        if (repository.hasUser())
                        {
                            navigationController.navigate(Screens.MainScreen.screen){
                                popUpTo(0)
                            }

                        }else
                        {
                            navigationController.navigate(Screens.RegisterScreen.screen){
                                popUpTo(0)
                            }

                        }

                        selected.value = Icons.Default.Add }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Register",
                            tint = GreenJC,
                            modifier = Modifier.size(26.dp)
                        )
                        
                    }
                }
                IconButton(onClick = {
                    if (repository.hasUser())
                    {
                        navigationController.navigate(Screens.WeatherScreen.screen){
                            popUpTo(0)
                        }

                    }else
                    {
                        navigationController.navigate(Screens.LoginScreen.screen){
                            popUpTo(0)
                        }

                    }

                    selected.value = Icons.Default.Cloud
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Default.Cloud,
                        contentDescription = "Weather",
                        tint = if (selected.value == Icons.Default.Cloud) Color.White else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }
                IconButton(onClick = {
                    if (repository.hasUser())
                    {
                        navigationController.navigate(Screens.ProfileScreen.screen){
                            popUpTo(0)
                        }

                    }else
                    {
                        navigationController.navigate(Screens.LoginScreen.screen){
                            popUpTo(0)
                        }

                    }

                    selected.value = Icons.Default.Person
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = if (selected.value == Icons.Default.Person) Color.White else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }


            }
        }
    ) { paddingValues ->
        NavHost(navController = navigationController, startDestination = Screens.MainScreen.screen, modifier = Modifier.padding(paddingValues)) {
            composable(Screens.MainScreen.screen) {
                if (repository.hasUser())
                {
                    MainScreen(context = context, navController = navigationController)
                }else{
                    LoginScreen(loginViewModel = LoginViewModel(),navController = navigationController)
                }

            }
            composable(Screens.ProfileScreen.screen) {
                if (repository.hasUser())
                {
                    ProfileScreen(navController = navigationController)
                }else
                {
                    LoginScreen(loginViewModel = LoginViewModel(),navController = navigationController)
                }

            }
            composable(Screens.LoginScreen.screen) {
                LoginScreen(loginViewModel = LoginViewModel(),navController = navigationController)
            }
            composable(Screens.WeatherScreen.screen) {
                if (repository.hasUser())
                {
                    WeatherScreen(navController = navigationController,locationKey ="318251",
                    locationName ="Istanbul",
                    country ="Türkiye")
                }else
                {
                    LoginScreen(loginViewModel = LoginViewModel(),navController = navigationController)
                }

            }
            composable(Screens.RegisterScreen.screen) {
                RegisterScreen(loginViewModel = LoginViewModel(),navController = navigationController)
            }
        }
    }
}

@Preview
@Composable
fun MyBottomAppBarPreview() {
    MyBottomAppBar()
}



