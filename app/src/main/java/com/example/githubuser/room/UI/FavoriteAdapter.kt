package com.example.githubuser.room.UI

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.DetilGithubUser
import com.example.githubuser.databinding.UserfavoriteBinding
import com.example.githubuser.room.RoomDiffCallBack
import com.example.githubuser.room.TableFavorite

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {
    private val listFavorite = ArrayList<TableFavorite>()
    
    fun setListUser(listUser: List<TableFavorite>) {
        val diffCallback = RoomDiffCallBack(listFavorite, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorite.clear()
        this.listFavorite.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = UserfavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }
    override fun getItemCount(): Int {
        return listFavorite.size
    }
    
    inner class FavViewHolder(private val binding: UserfavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tabel : TableFavorite) {
            with (binding) {
                Glide.with(imgFoto.context)
                    .load(tabel.avatar)
                    .circleCrop()
                    .apply(RequestOptions().override(50,50))
                    .into(imgFoto)
                tvUsername.text = tabel.userID
                itemView.setOnClickListener{
                    val intent = Intent(it.context, DetilGithubUser::class.java)
                    intent.putExtra(DetilGithubUser.HIDE_FAB, "DELETE")
                    intent.putExtra(DetilGithubUser.EXTRA_GITHUBUSER, tabel.userID)
                    it.context.startActivity(intent)
                }
            }
        }
    
    }
    
}