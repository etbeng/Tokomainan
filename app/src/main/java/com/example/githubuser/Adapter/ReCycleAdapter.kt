package com.example.githubuser.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.API.tableUsers
import com.example.githubuser.databinding.UserSpecBinding

class ReCycleAdapter(private val listGitUser : List<tableUsers>) : RecyclerView.Adapter<ReCycleAdapter.ListViewHolder>() {
    var itemDiKlik: ( (tableUsers) -> Unit)? = null
    
    class ListViewHolder(var binding: UserSpecBinding) : RecyclerView.ViewHolder(binding.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = UserSpecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listUser = listGitUser[position]
        Glide.with(holder.binding.imgFoto.context)
            .load(listUser.avatar)
            .circleCrop()
            .apply(RequestOptions().override(50,50))
            .into(holder.binding.imgFoto)
        holder.binding.tvUsername.text = listUser.userID
        holder.binding.tvRepos.text = listUser.repos_url
        holder.itemView.setOnClickListener{
            itemDiKlik?.invoke(listUser)
        }
    }
    
    override fun getItemCount(): Int {
        return listGitUser.size
    }
    
}