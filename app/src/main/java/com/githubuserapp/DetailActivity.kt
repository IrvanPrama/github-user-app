package com.githubuserapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.githubuserapp.fragments.adapters.ViewPagerAdapter
import com.githubuserapp.vModel.data.User
import com.githubuserapp.vModel.DetailViewModel
import com.githubuserapp.vModel.data.UserData
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_users.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        showLoading(true)

        val intent = intent.getParcelableExtra<User>(EXTRA_DATA) as UserData
        val getUsername = intent.username

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getUsername

        configDetailViewModel(getUsername!!)
        configViewPager()
    }

    private fun configDetailViewModel(getUsername: String) {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        viewModel.setDetailUser(getUsername, this)
        viewModel.getDetailUser().observe(this, Observer {

            Glide.with(this)
                .load(it.avatar)
                .into(findViewById<CircleImageView>(R.id.avatar_detail))

            findViewById<TextView>(R.id.repository_detail).text = it.repository.toString()
            findViewById<TextView>(R.id.follower_detail).text = resources.getString(R.string.followers, it.followers)
            findViewById<TextView>(R.id.following_detail).text = resources.getString(R.string.followings, it.followings)
            findViewById<TextView>(R.id.name_user_detail).text = it.name
            findViewById<TextView>(R.id.company_detail).text = it.company
            findViewById<TextView>(R.id.location_detail).text = it.location

            showLoading(false)
        })
    }

    private fun configViewPager() {
        val sectionsPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar_detail.visibility = View.VISIBLE
            avatar_detail.visibility = View.INVISIBLE
            tabs.visibility = View.INVISIBLE
            viewPager.visibility = View.INVISIBLE
        } else {
            progressBar_detail.visibility = View.GONE
            avatar_detail.visibility = View.VISIBLE
            tabs.visibility = View.VISIBLE
            viewPager.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}