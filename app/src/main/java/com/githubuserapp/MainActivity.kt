package com.githubuserapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var list = arrayListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.setHasFixedSize(true)

        list.addAll(getListUser())
        showRecyclerList()

    }

    fun getListUser(): ArrayList<User> {
        val txtName = resources.getStringArray(R.array.name)
        val txtUsername= resources.getStringArray(R.array.username)
        val txtFollower= resources.getStringArray(R.array.followers)
        val txtFollowing= resources.getStringArray(R.array.following)
        val txtLocation= resources.getStringArray(R.array.location)
        val txtCompany= resources.getStringArray(R.array.company)
        val imgPhoto = resources.getIntArray(R.array.avatar)


        val listUser = ArrayList<User>()
        for (position in imgPhoto.indices) {
            val user = User(
                    imgPhoto[position],
                    txtName[position],
                    txtUsername[position],
                    txtFollower[position],
                    txtFollowing[position],
                    txtCompany[position],
                    txtLocation[position]
            )
            listUser.add(user)
        }
        return listUser
    }

    private fun showRecyclerList() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = UserAdapter(list)
        binding.rvList.adapter = listUserAdapter
    }

}

