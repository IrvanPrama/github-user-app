package com.githubuserapp.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.githubuserapp.R
import com.githubuserapp.vModel.data.UserData
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter: RecyclerView.Adapter<UserAdapter.ListUserViewHolder>() {

    private val userData = ArrayList<UserData>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(item: ArrayList<UserData>) {
        userData.clear()
        userData.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_users, parent, false)
        )
    }

    override fun getItemCount(): Int = userData.size

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(userData[position])
    }

    inner class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItems: UserData) {
            with(itemView) {

                findViewById<TextView>(R.id.txt_name_user).text = userItems.username
                findViewById<TextView>(R.id.type).text = userItems.type
                Glide.with(itemView.context)
                    .load(userItems.avatar)
                    .into(findViewById<CircleImageView>(R.id.avatar))
                itemView.setOnClickListener { onItemClickCallback?.onIemClicked(userItems) }
            }
        }
    }
}

    interface OnItemClickCallback {
        fun onIemClicked(userItems: UserData)
    }


