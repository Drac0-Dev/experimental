package com.dracoapps.experimental.ui.screens.auth

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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dracoapps.experimental.R
import com.dracoapps.experimental.ui.data.local.User
import com.dracoapps.experimental.ui.viewmodels.RegistrationViewModel

@Composable
fun RegistrationScreen (navController: NavController, viewModel: RegistrationViewModel){
    //Observar el estado de una variable en ViewModel
    val regResult by viewModel.regResult.observeAsState(false)
    val email = remember { mutableStateOf("") }
    val password = remember{ mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    //val roleState = remember{ mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = remember {
                ScrollState(0)
            }, enabled = true),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = {email.value = it},
            label = {
                Row( Modifier
                    .size(width = 200.dp, height = 80.dp)
                    )
                     {
                         Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "Email")
                         Spacer(Modifier.width(10.dp))
                         Text ("Email")
                }
            },
            placeholder = { Text("Ingresa tu email", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier
            .size(width = 300.dp, height = 80.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = username.value,
            onValueChange = {username.value = it},
            label = {
                Row( Modifier
                    .size(width = 200.dp, height = 80.dp)
                )
                {
                    Icon(painter = painterResource(id = R.drawable.baseline_person_24), contentDescription = "Email")
                    Spacer(Modifier.width(10.dp))
                    Text ("Username")
                }
            },
            placeholder = { Text("Ingresa tu nombre de usuario", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier
                .size(width = 300.dp, height = 80.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password.value,
            onValueChange = {password.value = it},
            label = {
                Row( Modifier
                    .size(width = 200.dp, height = 80.dp)
                )
                {
                    Icon(painter = painterResource(id = R.drawable.baseline_key_24), contentDescription = "Email")
                    Spacer(Modifier.width(10.dp))
                    Text ("Password")
                }
            },
            placeholder = { Text("Ingresa tu password", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier
                .size(width = 300.dp, height = 80.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                fun showToast(message:String){
                    Toast.makeText(context,message, Toast.LENGTH_SHORT).apply{
                        setGravity(Gravity.CENTER,0,0)
                        show()
                    }
                }
                if (email.value.isEmpty() || password.value.isEmpty() || username.value.isEmpty()){
                    showToast("Debes ingresar todos los datos requeridos")
                }else{
                    viewModel.regNewUser(context,email.value, username.value, password.value)
                    showToast("Los datos proporcionados fueron los siguientes: ${email.value}, ${password.value} y ${username.value} \n")
                }
                if (regResult){
                    viewModel.upsertUser(User(id = 0, email = email.value, username = username.value, password = password.value, userId = null, isAuth = true, urlPic = null, description = null))
                    showToast("El registro de usuario fue exitoso")
                    //Navega a la siguiente pantalla
                    navController.navigate("Profile")
                }else {
                     showToast("No fue posible registrar al nuevo usuario")
                }
           },
            modifier = Modifier
                .size(width = 300.dp, height = 60.dp)
                .padding(top = 8.dp)
        ) {
            Text(text = "Enviar",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
        RegistrationScreen(navController = rememberNavController(), viewModel = RegistrationViewModel())
}
