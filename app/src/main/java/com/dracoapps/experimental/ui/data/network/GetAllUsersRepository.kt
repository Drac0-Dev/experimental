package com.dracoapps.experimental.ui.data.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.dracoapps.experimental.ui.data.items.UserItem
import org.json.JSONException

class GetAllUsersRepository  {
   private val url = "$API_URL/getAllUsersApiMovil.php?api=true"
    fun getUserList(context: Context, callback: (List<UserItem>) -> Unit) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val userList = mutableListOf<UserItem>()
                try {
                    for (i in 0 until response.length()) {
                        val user = response.getJSONObject(i)
                        val userId = user.getString("ID")
                        val username = user.getString("username")
                        val email = user.getString("email")
                        val description = user.getString("description")
                        //Este valor de urlPic es sólo una muestra
                        val urlPic = "https://loremflickr.com/400/400/people?lock="+"${i+1}"
                        userList.add(UserItem(userId, username, email, description, urlPic))
                    }
                    callback(userList)
                } catch (e: JSONException) {
                    Log.e("GetAllUsersRepository", "Error parsing JSON")
                }
            },
            { error ->
                Log.e("GetAllUsersRepository", "Error: ${error.message}")
            }
        )
        Volley.newRequestQueue(context).add(jsonArrayRequest)
    }
}
