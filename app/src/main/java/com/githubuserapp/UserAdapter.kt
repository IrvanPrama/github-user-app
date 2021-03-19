package com.githubuserapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter  internal constructor(private val context: Context) : BaseAdapter() {

    internal var users = arrayListOf<User>()

    override fun getCount(): Int = users.size

    override fun getItem(p0: Int): Any = users[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val user = getItem(position) as User
        viewHolder.bind(user)
        return itemView
    }

    private inner class ViewHolder(view: View) {
        private val txtName: TextView = view.findViewById(R.id.txt_name)
        private val txtUsername: TextView = view.findViewById(R.id.txt_username)
        private val imgPhoto: ImageView = view.findViewById(R.id.img_photo)
//        private val txtRepository: TextView = view.findViewById(R.id.txt_repository)
        private val txtFollower: TextView = view.findViewById(R.id.txt_follower)
        private val txtFollowing: TextView = view.findViewById(R.id.txt_following)
        private val txtLocation: TextView = view.findViewById(R.id.txt_location)
        private val txtCompany: TextView = view.findViewById(R.id.txt_company)

        fun bind(user: User) {
            txtName.text = user.name
            txtUsername.text = user.username
            imgPhoto.setImageResource(user.photo)
//            txtRepository.text = user.repository
            txtFollower.text = user.follower
            txtFollowing.text = user.following
            txtLocation.text = user.location
            txtCompany.text = user.company
        }
    }

}