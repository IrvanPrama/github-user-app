package com.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_USER= "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataUser = intent.getParcelableExtra<User>(KEY_USER) as User
        supportActionBar?.title = dataUser.name

        findViewById<TextView>(R.id.txt_name).text = dataUser.name
        findViewById<TextView>(R.id.txt_username).text = dataUser.username
        findViewById<ImageView>(R.id.img_photo).setImageResource(dataUser.photo)
        findViewById<TextView>(R.id.txt_follower).text = dataUser.follower
        findViewById<TextView>(R.id.txt_following).text = dataUser.following
        findViewById<TextView>(R.id.txt_company).text = dataUser.company
        findViewById<TextView>(R.id.txt_location).text = dataUser.location

    }

}