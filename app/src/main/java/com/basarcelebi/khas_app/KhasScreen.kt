@file:OptIn(ExperimentalMaterial3Api::class)

package com.basarcelebi.khas_app

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.basarcelebi.khas_app.screens.WeatherScreen

enum class KhasScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Weather(title = R.string.weather),
    Entree(title = R.string.hello_blank_fragment),
    SideDish(title = R.string.back_button),
    Accompaniment(title = R.string.app_name),
    Checkout(title = R.string.hello_blank_fragment)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KhasAppBar(
    currentScreen: KhasScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KhasApp(navController: NavHostController = rememberNavController()) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = KhasScreen.valueOf(
        backStackEntry?.destination?.route ?: KhasScreen.Start.name
    )

    Scaffold(
        topBar = {
            KhasAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->


        NavHost(
            navController = navController,
            startDestination = KhasScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = KhasScreen.Start.name) {
                MainScreen(navController = navController,onNextButtonClicked ={navController.navigate(KhasScreen.Weather.name)})
            }
            composable(route = "weather/318251/Istanbul/Türkiye"){
                WeatherScreen(navController = navController,
                    locationKey = "318251",
                    locationName = "Istanbul",
                    country = "Türkiye"
                )
            }
            composable(route = KhasScreen.SideDish.name) {
                MainScreen(navController = navController,onNextButtonClicked ={navController.navigate(KhasScreen.Checkout.name)})
            }
            composable(route = KhasScreen.Checkout.name) {
                MainScreen(navController = navController,onNextButtonClicked ={navController.navigate(KhasScreen.Start.name)})
            }
        }
    }

}