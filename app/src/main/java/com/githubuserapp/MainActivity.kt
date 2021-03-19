package com.githubuserapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private var users = arrayListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"Welcome", Toast.LENGTH_SHORT).show()

        val listView: ListView = findViewById(R.id.lv_list)
        adapter = UserAdapter(this)
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            val intentWithParcelable = Intent(this@MainActivity, DetailActivity::class.java)

            val selectedUser: User = users[position]

            intentWithParcelable.putExtra(DetailActivity.KEY_USER, selectedUser)
            startActivity(intentWithParcelable)
        }

    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataLocation = resources.getStringArray(R.array.location)
        dataCompany = resources.getStringArray(R.array.company)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                    dataPhoto.getResourceId(position, -1),
                    dataName[position],
                    dataUsername[position],
                    dataRepository[position],
                    dataFollower[position],
                    dataFollowing[position],
                    dataLocation[position],
                    dataCompany[position],
            )
            users.add(user)
        }
        adapter.users = users
    }


}

