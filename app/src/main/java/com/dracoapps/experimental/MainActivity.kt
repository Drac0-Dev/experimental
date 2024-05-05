package com.dracoapps.experimental

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.dracoapps.experimental.ui.theme.DefaultTheme
import com.dracoapps.experimental.ui.navigation.Nav


@RequiresApi(Build.VERSION_CODES.S)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember {mutableStateOf(false)}
            fun changeDarkMode(darkMode: Boolean) {
                isDarkMode = darkMode // Update state directly
                Log.d(
                    "Main Activity",
                    "La acción change DarkMode fue llamada desde el Profile Screen y el valor de IsDarkMode = $isDarkMode"
                ) // Use string template
            }
            Log.d("MainActivity", "El valor IsDarkMode = $isDarkMode") // Use string template
            // Pass the isDarkMode state as a parameter to the DefaultTheme composable
            DefaultTheme(darkTheme = isDarkMode) {
                Nav(changeDarkMode = :: changeDarkMode)
            }
        }
    }
}