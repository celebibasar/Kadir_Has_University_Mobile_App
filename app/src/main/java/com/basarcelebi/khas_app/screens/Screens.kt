package com.basarcelebi.khas_app.screens

sealed class Screens(val screen: String) {
    data object LoginScreen : Screens("loginScreen")
    data object MainScreen : Screens("mainScreen")
    data object ProfileScreen : Screens("profileScreen")
    data object RegisterScreen : Screens("registerScreen")
    data object WeatherScreen : Screens("weatherScreen")
}