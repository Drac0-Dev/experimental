package com.dracoapps.experimental.ui.data.network

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dracoapps.experimental.ui.data.items.AuthResponse
import org.json.JSONException
import org.json.JSONObject

class AuthUserRepository {

    fun authUser(context : Context, param: String, pwd: String, callback: (AuthResponse) -> Unit) {
        val url = "$API_URL/authApiMovil.php"
        //Solicitud HTTP usando POST
        val stringRequest = object : StringRequest(
            Method.POST, url, Response.Listener { response ->
                try {
                    val obj = JSONObject(response)
                    val isAuth = obj.getBoolean("success")
                    val userId = obj.getString("ID")
                    if (isAuth) {
                        callback(
                            AuthResponse(
                                isAuth = true ,
                                userId = userId
                            )
                        )
                        Log.d("AutthUserRepository","El valor de isAuth es: $isAuth ")
                        Log.d("AutthUserRepository","El valor de userId es: $userId ")
                    } else {
                        callback(
                            AuthResponse(
                                isAuth = isAuth ,
                                userId = null
                            ))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                //Manejo de error
                callback(
                    AuthResponse(
                        isAuth = false,
                        userId = null
                    )
                )
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["param"] = param
                params["pwd"] = pwd
                return params
            }
        }

        //Agregar solicitud a cola de Volley
        Volley.newRequestQueue(context).add(stringRequest)

        Log.d("AuthUserRepository", param)
        Log.d("AuthUserRepository", pwd)
    }
}