package com.dracoapps.experimental.ui.screens.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.dracoapps.experimental.ui.data.local.User
import com.dracoapps.experimental.ui.viewmodels.ProfileViewModel


@Composable
fun Profile ( viewModel : ProfileViewModel) {
    viewModel.getLastUser()
    val userData by viewModel.userLast.observeAsState(User(
        id = 0,
        userId = "",
        email = "",
        username = "",
        password = "",
        isAuth = false,
        urlPic = "",
        description = ""
    ))
    Log.d("Profile","El valor de UserData de Room es: $userData")
        ShowData(
            titles = listOf("Nombre:","Email:","Password:"),
           data = if (userData.username.toString().isNotEmpty()){
                listOf(userData.username.toString(), userData.email!!.toString(), userData.password!!.toString() )
            }else{
                listOf("Error", "Error", "Error")
            }
                ,
            description = if (userData.username.toString().isNotEmpty()){
                userData.description.toString()
            }else{
                "Error"
            }
        )
}
@Composable

fun ShowData (titles: List<String>, data:  List<String>, description:String) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Foto del perfil
        Picture()
        //Vista de Datos del Usuario
        titles.forEachIndexed{ index, title ->
            Spacer(modifier = Modifier.height(20.dp))
            UserData(title, data[index], index)
            Spacer(modifier = Modifier.height(10.dp))
        }
        //Vista de la Descripción
            Description(description)
        //Vista para eliminar cuenta
             Spacer(modifier = Modifier.height(10.dp))
            TextContainer("Eliminar Cuenta")
    }
}


@Composable
fun UserData (title :String, data: String, case: Int){
    var showDialog by remember { mutableStateOf(false) }
    var msg by remember {
        mutableStateOf("")
    }
    Row (
        modifier = Modifier
            .border(1.dp, color = (MaterialTheme.colorScheme.primary), shape = CircleShape)
            .padding(10.dp)
            .padding(horizontal = 10.dp)
            .width(200.dp)
            .clickable {
                msg = when (case + 1) {
                    1 -> "el nombre del usuario"
                    2 -> "el email"
                    3 -> "el password"
                    else -> {
                        "Opción inválida"
                    }
                }
                showDialog = true
            },
        horizontalArrangement = Arrangement.Center
    ){
        Text (
            modifier = Modifier,
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text (
            modifier = Modifier,
            text = data,
            style = MaterialTheme.typography.bodyLarge
        )
    }
    if (showDialog) {
        ShowAlertDialog("cambiar $msg tu perfil?", showDialog = remember {
            mutableStateOf(showDialog) }
        )
    }
}

@Composable
fun Description (data: String){
    var showDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxHeight(fraction = 0.75f)
            .fillMaxWidth(.85f)
            // .border(1.dp, color = (MaterialTheme.colorScheme.primary), shape = CircleShape)
            .padding(10.dp)
            .padding(horizontal = 10.dp)
            .width(200.dp)
            .clickable {
                showDialog = true
            }
        ,
    ){
        Text (
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            text = "Descripción:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text (modifier = Modifier
            .fillMaxHeight(1f)
            .padding(5.dp),
            text = data,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
    if (showDialog) {
        ShowAlertDialog("cambiar la descripción de tu perfil?", showDialog = remember {
            mutableStateOf(showDialog) }
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DescriptionPreview() {
    Description ("Esta es la descripción del usuario")
}
*/

@Composable
fun TextContainer (texto: String) {
    var showDialog by remember { mutableStateOf(false) }
    Row (
        modifier = Modifier
            .border(1.dp, color = (MaterialTheme.colorScheme.primary), shape = CircleShape)
            .padding(10.dp)
            .padding(horizontal = 10.dp)
            .width(200.dp)
            .height(20.dp)
            .clickable {
                showDialog = true
            },
        horizontalArrangement = Arrangement.Center
    ){
        Text (
            modifier = Modifier.align(Alignment.CenterVertically),
            text = texto,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Red,
        )
    }
    if (showDialog) {
        ShowAlertDialog("eliminar tu cuenta?", showDialog = remember {
            mutableStateOf(showDialog) }
        )
    }
}

@Composable
fun Picture () {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(300.dp)
    ){
AsyncImage(
    modifier = Modifier
        .clickable {
            showDialog = true
        }
        .height(260.dp)
        .width(260.dp),
    model = ImageRequest.Builder(LocalContext.current)
        .data("https://loremflickr.com/400/400/cat?lock=1")
        .transformations(CircleCropTransformation())
        .build(),
    contentDescription = "Foto de perfil",
    )
    }
    if (showDialog) {
        ShowAlertDialog("cambiar la foto de tu perfil?", showDialog = remember {
            mutableStateOf(showDialog) }
        )
    }
}

@Composable
fun ShowAlertDialog ( msg:String, showDialog : MutableState<Boolean>) {
    val context = LocalContext.current
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {showDialog.value = false},
            title = { Text("Atención") },
            text = { Text("¿Deseas $msg") },
            confirmButton  = {
                Text( modifier = Modifier.clickable {
                    Toast.makeText(context, "Aquí se implementará la lógica para cambiar el dato", Toast.LENGTH_SHORT).show()
                    showDialog.value = false }, text = "Ok")
            },
            dismissButton = {
                Text(modifier = Modifier.clickable { showDialog.value = false}, text = "Cancelar")
            }
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    // Crea un NavController de ejemplo para el Preview
    Profile (ProfileViewModel())
}
*/



@Preview(showBackground = true)
@Composable
fun ShowDataPreview(){
    ShowData(titles = listOf("Nombre:","Email:","Password:"), data = listOf("User1","user1@mail.com","*****"), description = "Hola esta es una descripción del usuario para ver cómo se comporta el texto cuando ")
}
