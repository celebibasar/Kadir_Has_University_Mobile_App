@file:OptIn(ExperimentalMaterial3Api::class)

package com.basarcelebi.khas_app

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.basarcelebi.khas_app.screens.LoginScreen
import com.basarcelebi.khas_app.screens.MainScreen
import com.basarcelebi.khas_app.screens.ProfileScreen
import com.basarcelebi.khas_app.screens.WeatherScreen


enum class KhasScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
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

@Composable
fun KhasApp(context: Context) {


    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        composable("home"
            ){
            MainScreen(context=context,navController = navController,
                locationKey = it.arguments?.getString("location_key") ?:"318251",
                locationName = it.arguments?.getString("name") ?:"Istanbul",
                country = it.arguments?.getString("country") ?:"Türkiye")
        }
        composable("weather/{location_key}/{name}/{country}", arguments = listOf(
            navArgument("location_key"){
                type = NavType.StringType
            },
            navArgument("name"){
                type = NavType.StringType
            },
            navArgument("country"){
                type = NavType.StringType
            }
        )){
            WeatherScreen(navController = navController,
                locationKey = it.arguments?.getString("location_key") ?:"318251",
                locationName = it.arguments?.getString("name") ?:"Istanbul",
                country = it.arguments?.getString("country") ?:"Türkiye"
            )
        }
        composable(route = "profile") {
            ProfileScreen(navController = navController)
        }

    }


}