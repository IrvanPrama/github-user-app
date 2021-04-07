package com.githubuserapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubuserapp.databinding.ItemUserBinding


class UserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ListViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(user: User) {
                with(binding) {
                    Glide.with(itemView.context)
                            .load(user.photo)
                            .apply(RequestOptions().override(55, 55))
                            .into(avatar)

                    txtName.text = user.name
                    txtUsername.text = user.username

                    itemView.setOnClickListener { onItemClickCallback?.onItemClicked(user) }
                }
            }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}