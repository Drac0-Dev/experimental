package com.dracoapps.experimental.ui.screens.profile

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dracoapps.experimental.MainActivity
import com.dracoapps.experimental.R
import com.dracoapps.experimental.ui.screens.community.Community
import com.dracoapps.experimental.ui.viewmodels.CommunityViewModel
import com.dracoapps.experimental.ui.viewmodels.LoginViewModel
import com.dracoapps.experimental.ui.viewmodels.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: LoginViewModel , changeDarkMode: (Boolean)->Unit) {
    val context = LocalContext.current
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        Scaffold(
            Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = if (isSystemInDarkTheme()){
                MaterialTheme.colorScheme.secondary
            }else{MaterialTheme.colorScheme.background},
            topBar = {
                CenterAlignedTopAppBar(
                    colors=
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(text = "Experimental",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = { var expanded by  remember { mutableStateOf(false) }
                         IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menú principal"
                        )
                    }
                        //DropDownMenu Logic
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            //Close App Item
                            Row (
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp, 3.dp, 3.dp, 3.dp)
                                    .align(Alignment.CenterHorizontally)
                            ){
                                Icon(
                                    modifier = Modifier.align(
                                        Alignment.CenterVertically
                                    ),
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Menú principal"
                                )
                                DropdownMenuItem(
                                    text ={Text("Salir de la App")},
                                    onClick = {
                                        val intent = Intent(context, MainActivity::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        (context as MainActivity).finish()
                                    }
                                )
                            }
                            //Close Session Item
                            Row (
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp, 3.dp, 3.dp, 3.dp)
                                    .align(Alignment.CenterHorizontally)
                            ){
                                Icon(
                                    modifier = Modifier.align(
                                        Alignment.CenterVertically
                                    ),
                                    imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                                    contentDescription = "Menú principal"
                                )
                                DropdownMenuItem(
                                    text ={Text("Cerrar Sesión")},
                                    onClick = {
                                        viewModel.deleteAllUsers()
                                        viewModel.resetAutoInc()
                                        val intent = Intent(context, MainActivity::class.java)
                                        (context as MainActivity).finish()
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    })
                            }
                            //Mode
                            Row (
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp, 3.dp, 3.dp, 3.dp)
                                    .align(Alignment.CenterHorizontally)
                            ){
                                var isChecked by remember { mutableStateOf(false) }
                                if (isChecked) {
                                    Image(
                                        modifier = Modifier.align(
                                            Alignment.CenterVertically
                                        )
                                        ,
                                        painter = painterResource(id = R.drawable.baseline_dark_mode_24),
                                        contentDescription = "Modo Oscuro"
                                    )
                                    Log.d("ProfileScreen", "El usuario eligió el modo oscuro")
                                    changeDarkMode(true)
                                }else{
                                    Image(
                                        modifier = Modifier.align(
                                            Alignment.CenterVertically
                                        ),
                                        painter = painterResource(id = R.drawable.baseline_light_mode_24),
                                        contentDescription = "Modo Claro"
                                    )
                                    Log.d("ProfileScreen","El usuario eligió el modo claro")
                                    changeDarkMode(false)
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Switch(
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    checked = isChecked,
                                    onCheckedChange = {
                                    isChecked = it}
                                )
                            }
                        }//Aquí termina el DropdownMenu
                    },//Actions  top bar closure
                scrollBehavior = scrollBehavior,
                )
            },
        ) {
                innerPadding->
            val state  =  remember{ mutableStateOf(0) }
            val titles = listOf("Perfil", "Comunidad")
            Box(
                //This Box include all views even profile and community
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                PrimaryTabRow(
                    selectedTabIndex = state.value) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = state.value == index,
                            onClick = {state.value = index},
                            text = { Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                        )
                    }
                }
            when (state.value + 1){
                1-> Box ( modifier = Modifier.padding(innerPadding)){
                    Profile(ProfileViewModel()) }
                2-> Box ( modifier = Modifier.padding(innerPadding)) {
                    Community(CommunityViewModel()) }
           }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    // Crea un NavController de ejemplo para el Preview
    ProfileScreen(LoginViewModel(), changeDarkMode ={})
}