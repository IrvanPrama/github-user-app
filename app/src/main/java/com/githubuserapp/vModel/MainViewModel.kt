package com.githubuserapp.vModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubuserapp.MainActivity
import com.githubuserapp.vModel.data.UserData
import com.loopj.android.http.AsyncHttpClient
import cz.msebera.android.httpclient.Header
import com.loopj.android.http.AsyncHttpResponseHandler
import org.json.JSONObject

class MainViewModel: ViewModel() {
    val arrayUser = MutableLiveData<ArrayList<UserData>>()

    fun putUsers(context: Context, que: String) {

        val objList = ArrayList<UserData>()
        val client = AsyncHttpClient()

        val apiToken = "api"
        val url = "https://api.github.com/search/users?q=$que"

        client.addHeader("Authorization", "token $apiToken")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(

                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?

            ) {
                try {

                    val result = String(responseBody!!)
                    val resultObj = JSONObject(result)
                    val list = resultObj.getJSONArray("items")
                    Log.d(MainActivity::class.java.simpleName,result)

                    for (i in 0 until list.length()) {
                        val usersObj = list.getJSONObject(i)
                        val objectItem = UserData()
                        objectItem.username = usersObj.getString("login")
                        objectItem.avatar = usersObj.getString("avatar_url")
                        objectItem.type = usersObj.getString("type")
                        objList.add(objectItem)
                    }

                    arrayUser.postValue(objList)

                }
                catch (e: Exception) {
                    Toast.makeText(context, "Hey, something wrong? unable to Connect!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {

                Toast.makeText(context, "Hey, something wrong, check u'r connection: $statusCode", Toast.LENGTH_SHORT).show()

            }

        })

    }

    fun getUser(): LiveData<ArrayList<UserData>> {
        return arrayUser
    }

}