package com.dracoapps.experimental.ui.data.network

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RegUserRepository {
    fun regNewUser(contex : Context, email:String, username: String, password: String, callback: (Boolean) -> Unit){
        val url = "$API_URL/insertNewUserAPI.php"
        //Solicitud HTTP usando POST
        val stringRequest = object : StringRequest (
            Method.POST,url, Response.Listener { response ->
                try {
                    val obj = JSONObject(response)
                    if (!obj.getBoolean("success")) {
                        callback(false)
                    }else{
                        callback(true)
                    }
                }catch (e:JSONException){
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                //Manejo de error
                callback(false)
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["email"] = email
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        //Agregar solicitud a cola de Volley
        Volley.newRequestQueue(contex).add(stringRequest)

        Log.d("RegUserRepository", email)
        Log.d("RegUserRepository", username)
        Log.d("RegUserRepository", password)

    }
}