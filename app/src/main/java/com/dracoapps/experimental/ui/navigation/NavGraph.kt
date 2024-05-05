package com.dracoapps.experimental.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dracoapps.experimental.ui.screens.auth.LoginScreen
import com.dracoapps.experimental.ui.screens.profile.ProfileScreen
import com.dracoapps.experimental.ui.viewmodels.LoginViewModel
import com.dracoapps.experimental.ui.screens.auth.RegistrationScreen
import com.dracoapps.experimental.ui.viewmodels.RegistrationViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Nav( changeDarkMode: (Boolean)->Unit) {
    val navController = rememberNavController()
    NavHost (
        navController = navController,
        startDestination = "Login"
    ){
        composable(
            route = "Login"
        ){
            LoginScreen(navController, LoginViewModel()) }
        composable(
            route = "Registration"
        ){ RegistrationScreen(
            navController,
            RegistrationViewModel()
        ) }
        composable(
            route = "Profile"
        ){
            ProfileScreen(LoginViewModel(), changeDarkMode)
        }
    }
}