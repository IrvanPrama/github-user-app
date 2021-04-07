package com.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubuserapp.fragments.FollowersFragment
import com.githubuserapp.fragments.FollowingsFragment
import com.githubuserapp.fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_USER = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataUser = intent.getParcelableExtra<User>(KEY_USER) as User
        supportActionBar?.title = dataUser.username

        findViewById<TextView>(R.id.name_user).text = dataUser.name

        Glide.with(this)
            .load(dataUser.photo)
            .placeholder(R.drawable.ic_launcher_background)
            .apply(RequestOptions().override(55, 55))
            .into(findViewById<CircleImageView>(R.id.avatar))

        findViewById<TextView>(R.id.follower).text = dataUser.follower
        findViewById<TextView>(R.id.following).text = dataUser.following
        findViewById<TextView>(R.id.company).text = dataUser.company
        findViewById<TextView>(R.id.location).text = dataUser.location
        findViewById<TextView>(R.id.repository).text = dataUser.repository
        setUpTabs()
    }

    private fun setUpTabs() {
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FollowersFragment(), "Pengikut")
        adapter.addFragment(FollowingsFragment(), "Mengikuti")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setText(R.string.txt_follower)
        tabs.getTabAt(1)!!.setText(R.string.txt_following)

    }

}