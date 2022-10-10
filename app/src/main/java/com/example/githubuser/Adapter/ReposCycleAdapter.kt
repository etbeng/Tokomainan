package com.example.githubuser.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.API.reposUser
import com.example.githubuser.databinding.ReposSpecBinding

class ReposCycleAdapter(private val listGitUser: List<reposUser>): RecyclerView.Adapter<ReposCycleAdapter.ListViewHolder>() {
    var itemDiKlik: ( (reposUser) -> Unit)? = null
    
    class ListViewHolder(var binding: ReposSpecBinding) : RecyclerView.ViewHolder(binding.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ReposSpecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listUser = listGitUser[position]
        
        holder.binding.tvNamaRepos.text = listUser.description
        holder.binding.tvHTMLRepos.text = listUser.htmlUrl
        holder.itemView.setOnClickListener{
            itemDiKlik?.invoke(listUser)
        }
    }
    
    override fun getItemCount(): Int {
        return listGitUser.size
    }
}