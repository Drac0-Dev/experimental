package com.dracoapps.experimental.ui.screens.community

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.dracoapps.experimental.ui.data.items.UserItem

@Composable
fun Item (userItem: UserItem) {
    val context = LocalContext.current
    Column (
        Modifier
            .fillMaxWidth()
            .background(Color.White, CircleShape)) {
        Row{
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Hiciste click al usuario con el ID: ${userItem.userId}",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                .background(Color.White, CircleShape)
        Spacer(Modifier
                .width(40.dp)
            )
        Box  (
             modifier = Modifier
             .padding(10.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .padding(10.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userItem.urlPic.toString())
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = "Foto de perfil",
            )
        }

        Column (
            Modifier
                .fillMaxWidth(1f)
                .height(80.dp)
                .padding(2.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(40.dp)
                    .padding(15.dp, 15.dp, 2.dp, 0.dp),
                text = userItem.username.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(60.dp)
                   .padding(15.dp, 0.dp, 2.dp, 0.dp),
                text = userItem.email.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
        Row {
            Modifier
            Spacer(modifier = Modifier.width(0.dp).weight(1f))
            Text(
            modifier = Modifier
                .width(0.dp).weight(9f)
                .padding(10.dp, 10.dp, 10.dp, 20.dp),
            text = if (userItem.description.toString() != "null")
            {userItem.description.toString()}
            else
            {"Este usuario aún no tiene una descripción"},
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier
                .width(0.dp)
                .weight(1f)
            )
        }
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


@Preview (showBackground = true)
@Composable
fun ItemPreview() {
    Item (userItem = UserItem(
        userId = "1",
        username = "Usuario 1",
        email = "user1@mail.com",
        description = "Hola soy el usuario 1",
        urlPic = "https://loremflickr.com/400/400/people/1/")
    )
}