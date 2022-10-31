package com.example.githubuser.room.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivityFavoriteBinding
import com.example.githubuser.room.RoomVMFactory
import com.example.githubuser.room.RoomViewModel

class FavoriteActivity : AppCompatActivity() {
    private var _binding : ActivityFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var adapter : FavoriteAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        
        val roomVM = obtainViewModel(this)
        roomVM.getAllFavVM().observe(this, { it ->
            if (it != null) {
               adapter.setListUser(it)
            }
        })
        
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        
        supportActionBar?.title = "Favorite Users"
        adapter = FavoriteAdapter()
        binding?.rvListFavorite?.layoutManager = LinearLayoutManager(this)
        binding?.rvListFavorite?.setHasFixedSize(true)
        binding?.rvListFavorite?.adapter = adapter
    }
    
    private fun obtainViewModel(activity: AppCompatActivity) : RoomViewModel {
        val factory = RoomVMFactory.getInstance(activity.application)
        
        return ViewModelProvider(activity, factory).get(RoomViewModel::class.java)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}