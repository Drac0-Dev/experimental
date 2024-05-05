package com.dracoapps.experimental.ui.screens.auth

import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dracoapps.experimental.ui.viewmodels.LoginViewModel
import com.dracoapps.experimental.R
import com.dracoapps.experimental.ui.data.items.AuthResponse
import com.dracoapps.experimental.ui.data.items.UserItem
import com.dracoapps.experimental.ui.data.local.User

@Composable
    fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    viewModel.getUserRoom()
    val isAuth by viewModel.userRoom.observeAsState()
    if (isAuth?.isAuth == true) {
        IsUserAuth( navController)
    } else
    { LoginForm(viewModel, navController)
    }
}

@Composable
fun IsUserAuth(navController: NavController){
    navController.navigate("Profile")
}

@Composable
fun LoginForm (viewModel: LoginViewModel, navController: NavController) {
    val param = remember { mutableStateOf("") }
    val pwd = remember { mutableStateOf("") }
    val isAuth by viewModel.authResult.observeAsState(
        initial = AuthResponse(
            isAuth = false,
            userId = null
        )
    )
    val userItem by viewModel.user.observeAsState(
        initial = UserItem(
            userId = "",
            username = "",
            email = "",
            description = "",
            urlPic = ""
        )
    )
    val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = remember {
                    ScrollState(0)
                }, true),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = param.value,
                onValueChange = { param.value = it },
                label = {
                    Row( Modifier
                        .size(width = 200.dp, height = 80.dp)
                    )
                    {
                        Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "Email")
                        Spacer(Modifier.width(10.dp))
                        Text ("Email or username",
                            style = MaterialTheme.typography.bodySmall,
                            )
                    }
                },
                placeholder = { Text("Ingresa tu email o username",
                        style = MaterialTheme.typography.bodySmall) },
                modifier = Modifier
                    .size(width = 300.dp, height = 80.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = pwd.value,
                onValueChange = { pwd.value = it },
                label = {
                    Row( Modifier
                        .size(width = 200.dp, height = 80.dp)
                    )
                    {
                        Icon(painter = painterResource(id = R.drawable.baseline_key_24), contentDescription = "Email")
                        Spacer(Modifier.width(10.dp))
                        Text ("Password",
                            style = MaterialTheme.typography.bodySmall,
                            )
                    }
                },
                placeholder = { Text("Ingresa tu password",
                    style = MaterialTheme.typography.bodySmall) },
                modifier = Modifier
                    .size(width = 300.dp, height = 80.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = {
                    // Lógica para autenticar al usuario usando el email y password
                    viewModel.authUser(context = context, param = param.value, pwd = pwd.value)
                    if (isAuth.isAuth == true){
                        viewModel.deleteAllUsers()
                        viewModel.resetAutoInc()
                        viewModel.getUserById(context, isAuth.userId.toString())
                        Log.d("LoginScreen","El valor de UserId es: ${isAuth.userId.toString()}")
                        Log.d("LoginScreen","El valor de emaiil es: ${userItem!!.email.toString()}")
                        Log.d("LoginScreen","El valor de username es: ${userItem!!.username.toString()}")
                        Log.d("LoginScreen","El valor de password es: ${pwd.value}")
                        Log.d("LoginScreen","El valor de isAuth es: ${isAuth.isAuth.toString()}")
                        Log.d("LoginScreen","El valor de urlPic es: ${"https://loremflickr.com/400/400/people/1/l"}")
                        Log.d("LoginScreen","El valor de description es: ${userItem!!.description.toString()}")

                        if (userItem!!.email.toString().isNotEmpty()){
                             navController.navigate("Profile")
                            viewModel.upsertUser(
                                User(
                                    id = 0,
                                    userId = isAuth.userId.toString(),
                                    email = userItem!!.email.toString(),
                                    username = userItem!!.username.toString(),
                                    password = pwd.value,
                                    isAuth = true,
                                    urlPic = "https://loremflickr.com/400/400/people/1/l",
                                    description = userItem!!.description.toString()
                                )
                            )
                        }
                    }else{
                        Toast.makeText(context,"Credenciales Incorrectas", Toast.LENGTH_SHORT).apply{
                            setGravity(Gravity.CENTER,0,0)
                            show()
                        }
                    }
                },
                modifier = Modifier
                    .size(width = 300.dp, height = 60.dp)
                    .padding(top = 8.dp)
            ) {
                Text(text = "Iniciar sesión",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(
                onClick = {
                    navController.navigate("Registration") // Navegar a la vista de registro
                },
                modifier = Modifier
                    .size(width = 300.dp, height = 60.dp)
                    .padding(top = 8.dp)
            ) {
                Text(text = "Registrar nuevo usuario",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun LoginScreenPreview() {
        // Crea un NavController de ejemplo para el Preview
        LoginScreen(navController = rememberNavController(), viewModel = LoginViewModel())
    }
