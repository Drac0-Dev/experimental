package com.dracoapps.experimental.ui.screens.community


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dracoapps.experimental.ui.data.items.UserItem
import com.dracoapps.experimental.ui.viewmodels.CommunityViewModel

@Composable
fun Community (viewModel: CommunityViewModel) {
val userList : List<UserItem> by viewModel.userList.observeAsState(initial = emptyList())
viewModel.getAllUsers(LocalContext.current)
Column (  modifier = Modifier
    .padding(20.dp),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start)
    {
        LazyColumn (
        ){
            items (userList)  { item->
                Surface(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                ) {
                    Item(userItem = item
                    )
                }
            }
        }
    }
}

//TODO: crear un viewmodel para la community screen - ok
//TODO:crear repositorio para obtener los datos de los usuarios del servidor - ok
//TODO: Conectar el estado de la variable a la vista-ok
//TODO:Probar-ok
//TODO:Corregir manejo de nulos-ok


@Preview(showBackground = true)
@Composable
fun CommunityPreview() {
    // Crea un NavController de ejemplo para el Preview
    Community( CommunityViewModel())
}