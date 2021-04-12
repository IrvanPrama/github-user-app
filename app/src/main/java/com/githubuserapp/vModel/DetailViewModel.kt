package com.githubuserapp.vModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubuserapp.DetailActivity
import com.githubuserapp.MainActivity
import com.githubuserapp.vModel.data.User
import com.loopj.android.http.AsyncHttpClient
import cz.msebera.android.httpclient.Header
import com.loopj.android.http.AsyncHttpResponseHandler
import org.json.JSONObject

class DetailViewModel: ViewModel() {
    val listUsersDetail = MutableLiveData<User>()

    fun setDetailUser(getUsername: String?, context: Context) {

        val apiKey = "api"
        val url = "https://api.github.com/users/$getUsername"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $apiKey")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                try {

                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val userItemsDetail =
                        User()

                    userItemsDetail.name = responseObject.getString("login")
                    userItemsDetail.avatar = responseObject.getString("avatar_url")
                    userItemsDetail.company = responseObject.getString("company")
                    userItemsDetail.location = responseObject.getString("location")
                    userItemsDetail.repository = responseObject.getInt("public_repos")
                    userItemsDetail.followers = responseObject.getInt("followers")
                    userItemsDetail.followings = responseObject.getInt("following")

                    listUsersDetail.postValue(userItemsDetail)

                } catch (e: Exception) {
                    Toast.makeText(context, "Unable to Connect", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Toast.makeText(context, "Unable to Connect: $statusCode", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDetailUser(): LiveData<User> {
        return listUsersDetail
    }


}