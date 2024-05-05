package com.dracoapps.experimental.ui.data.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dracoapps.experimental.ui.data.items.UserItem

class GetUserByIdRepository {
    fun getUserById(context: Context, userid: String, callback: (UserItem)->Unit) {
        val url = "$API_URL/getUserByIdApi.php?userId=$userid"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                if (response != null) {
                    val userId  = response.getString("ID")
                    val username= response.getString("username")
                    val email= response.getString("email")
                    val description= response.getString("description")
                    val urlPic = "https://loremflickr.com/400/400/people?lock=\"+\"${userid + 1}"
                    callback(
                        UserItem(
                            userId,
                            username,
                            email,
                            description,
                            urlPic
                        )
                    )
                } else{
                    callback(UserItem(null, null, null, null, null))
                }
                Log.d("GetUserRepository","Los datos del usuario fueron: $response")
            },
            { _ ->
                callback(UserItem(null, null, null, null, null))
            }
        )
        Volley.newRequestQueue(context).add(jsonObjectRequest)
    }
}

