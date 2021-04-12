package com.githubuserapp.fragments.adapters

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.githubuserapp.R
import com.githubuserapp.fragments.FollowersFragment
import com.githubuserapp.fragments.FollowingsFragment

class ViewPagerAdapter(private val mContext: Context, supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = listOf(
        FollowersFragment(),
        FollowingsFragment()
    )

    override fun getCount(): Int {return 2}

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    @StringRes
    private val mFragmentTitleList = intArrayOf(
        R.string.txt_follower,
        R.string.txt_following
    )

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(mFragmentTitleList[position])
    }

}