package com.dracoapps.experimental.ui.data.items

data class UserItem(
    val userId : String?,
    val username: String?,
    val email: String?,
    val description: String?,
    val urlPic: String?
)


/*
//Función para testear el LazyColum se usa para pasar el valor de LisItem
fun genListItem () = (1..10).map{
    UserItem (
        userId = it.toString(),
        username = "User$it",
        email = "user$it@mail.com",
        description = "Hola soy el usuario $it",
        urlPic = "https://loremflickr.com/400/400/people?lock=$it"
    )
}
*/

