package com.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val txtRepository= resources.getStringArray(R.array.repository)
        val txtFollowing= resources.getStringArray(R.array.following)
        val txtFollower= resources.getStringArray(R.array.followers)
        val txtCompany= resources.getStringArray(R.array.company)
        val txtLocation= resources.getStringArray(R.array.location)
        val imgPhoto = resources.getStringArray(R.array.photo)


        val listUser = ArrayList<User>()
        for (position in txtName.indices) {
            val user = User(
                    txtName[position],
                    txtUsername[position],
                    txtRepository[position],
                    txtFollowing[position],
                    txtFollower[position],
                    txtCompany[position],
                    txtLocation[position],
                    imgPhoto[position]
            )
            listUser.add(user)
        }
        return listUser
    }

    private fun showRecyclerList() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(list)
        binding.rvList.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: User) {
                showSelectedUser(user)
            }
        })
    }


    private fun showSelectedUser(user: User) {
//        Toast.makeText(this, "Kamu memilih ${user.name}", Toast.LENGTH_SHORT).show()
        val dataUser = User(
                user.name,
                user.username,
                user.repository,
                user.following,
                user.follower,
                user.location,
                user.company,
                user.photo
        )

        val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
        intentDetail.putExtra(DetailActivity.KEY_USER, dataUser)
        startActivity(intentDetail)
    }
}

