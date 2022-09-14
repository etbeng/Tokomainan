package com.example.githubuser

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.databinding.UserSpecBinding
import kotlinx.parcelize.Parcelize

@Parcelize
data class gitHubUser (
    var userID : String = "",
    var username : String = "",
    var email: String = "",
    var company : String = "",
    var location : String = "",
    var photo : String = "",
    var follower : Int = 0,
    var follwing : Int = 0,
    var repository : Int = 0
) : Parcelable

class ListGitHubAdapter(private val listGitUser : ArrayList<gitHubUser>) : RecyclerView.Adapter<ListGitHubAdapter.ListViewHolder>() {
    var itemDiKlik: ( (gitHubUser) -> Unit)? = null
    
    class ListViewHolder(var binding: UserSpecBinding) : RecyclerView.ViewHolder(binding.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = UserSpecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listUser = listGitUser[position]
        Glide.with(holder.binding.imgFoto.context)
            .load(listUser.photo)
            .circleCrop()
            .apply(RequestOptions().override(50,50))
            .into(holder.binding.imgFoto)
        holder.binding.tvUsername.text = listUser.username + " (" + listUser.userID + ")"
        holder.binding.tvFwer.text = listUser.follower.toString() + " Followers"
        holder.binding.tvFwing.text = listUser.follwing.toString() + " Following"
        
        holder.itemView.setOnClickListener{
            itemDiKlik?.invoke(listUser)
        }
    }
    
    override fun getItemCount(): Int {
        return listGitUser.size
    }
    
}