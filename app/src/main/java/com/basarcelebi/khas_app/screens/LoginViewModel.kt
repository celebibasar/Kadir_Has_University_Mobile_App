package com.basarcelebi.khas_app.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.basarcelebi.khas_app.MainActivity
import com.basarcelebi.khas_app.repositories.AuthRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()

) : ViewModel() {
    val currentUser = repository.currentUser




    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(userName: String) {
        loginUiState = loginUiState.copy(userName = userName)
    }
    fun navigateToHome(navController: NavController) {
        if (loginUiState.isSuccessLogin)
        {
            navController.navigate(Screens.MainScreen.screen)
        }

    }

    fun onPasswordNameChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    fun onUserNameChangeSignup(userName: String) {
        loginUiState = loginUiState.copy(userNameSignUp = userName)
    }

    fun onPasswordChangeSignup(password: String) {
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }

    fun onConfirmPasswordChange(password: String) {
        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.password.isNotBlank()

    private fun validateSignupForm() =
        loginUiState.userNameSignUp.isNotBlank() &&
                loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmPasswordSignUp.isNotBlank()


    fun createUser(context: Context, navController: NavController) = viewModelScope.launch {
        try {
            if (!validateSignupForm()) {
                throw IllegalArgumentException("email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passwordSignUp !=
                loginUiState.confirmPasswordSignUp
            ) {
                throw IllegalArgumentException(
                    "Password do not match"
                )

            }
            loginUiState = loginUiState.copy(signUpError = null)
            repository.createUser(
                loginUiState.userNameSignUp,
                loginUiState.passwordSignUp
            ) { isSuccessful ->
                loginUiState = if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "Success Register",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        "Failed Register",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState.copy(isSuccessLogin = false)
                }

            }


        } catch (e: Exception) {
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }


    }

    fun loginUser(context: Context, navController: NavController, onComplete: (Boolean) -> Unit) = viewModelScope.launch {
        try {
            if (!validateLoginForm()) {
                throw IllegalArgumentException("email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.userName,
                loginUiState.password
            ) { isSuccessful ->
                loginUiState = if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "Success Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState.copy(isSuccessLogin = true)



                } else {
                    Toast.makeText(
                        context,
                        "Failed Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState.copy(isSuccessLogin = false)
                }
                onComplete(isSuccessful)

            }


        } catch (e: Exception) {
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }


    }

    fun logout() {
        repository.logout()
    }


}

data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val userNameSignUp: String = "",
    val passwordSignUp: String = "",
    val confirmPasswordSignUp: String = "",
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null,
)

















