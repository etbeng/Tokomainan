package com.example.githubuser

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuser.API.DetilUser
import com.example.githubuser.Adapter.PagerAdapter
import com.example.githubuser.ViewModel.DetilViewModel
import com.example.githubuser.databinding.ActivityDetilGithubUserBinding
import com.example.githubuser.room.FavoriteViewModel
import com.example.githubuser.room.RoomVMFactory
import com.example.githubuser.room.TableFavorite
import com.google.android.material.tabs.TabLayoutMediator

class DetilGithubUser : AppCompatActivity() {
    
    private lateinit var binding : ActivityDetilGithubUserBinding
    private val detilModel by viewModels<DetilViewModel>()
    //room
    private var tableFav : TableFavorite? = null
    private lateinit var favoriteVM : FavoriteViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityDetilGithubUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        favoriteVM = obtainViewModel(this)
        
        //kalau link berasal dari daftar favorite ganti dengan icon hapus
        val statusIcon = intent?.getStringExtra(HIDE_FAB) ?: "false"
        if (statusIcon == "DELETE") {
            binding.favAdd.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_delete_48))
        }
        
        //observe query detiluser dan loading
        val userID = intent?.getStringExtra(EXTRA_GITHUBUSER) ?: ""
        //query ke jaringan
        detilModel.queryDetilUser(userID)
        detilModel.gitUser.observe(this) { user ->
            //isi ke tabel favorite
            tableFav = TableFavorite()
            tableFav?.userID = user.username.toString()
            tableFav?.avatar = user.avatar.toString()
            displayDetilUsers(user)
        }
        detilModel.isLoading.observe(this) {
            showLoading(it)
        }
        
        //observe ada / tidak user id di favorite
        favoriteVM.getSelectedVM(userID).observe(this) {
            if (it?.userID != null && statusIcon == "false") { binding.favAdd.hide()}
        }
        
        binding.favAdd.setOnClickListener{
            if (statusIcon == "DELETE") {
                favoriteVM.deleteVM(tableFav as TableFavorite)
                showToast("User ${tableFav?.userID} sudah dihapus dari Favorite !")
            } else {
                favoriteVM.insertVM(tableFav as TableFavorite)
                showToast("User ${tableFav?.userID} sudah disimpan di Favorite !")
            }
            binding.favAdd.hide()
        }
    
        //Tablayout
        binding.viewPager.adapter = PagerAdapter(this, userID)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
        supportActionBar?.elevation = 0f
        
        //caption untuk masing2 activity
        supportActionBar?.title = "Github User Profile"
        
    }
    
    private fun showToast(pesan: String) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pBar.visibility = View.VISIBLE
        } else {
            binding.pBar.visibility = View.GONE
        }
    }
    
    @SuppressLint("SetTextI18n")
    private fun displayDetilUsers(user: DetilUser?) {
        Glide.with(this@DetilGithubUser)
            .load(user?.avatar)
            .circleCrop()
            //.apply(RequestOptions().override(55,55))
            .into(binding.imgFotoDetil)
        binding.tvUserID.text = user?.username
        binding.tvUserDetil.text = user?.name
        binding.tvFwerDetil.text =  user?.fwers.toString() + " Followers"
        binding.tvFwingDetil.text = user?.fwing.toString() + " Following"
        binding.tvRepoDetil.text = user?.repos.toString() + " Repositories"
    }
    
    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = RoomVMFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }
    
    companion object {
        private val TAB_TITLES = arrayListOf("Followers", "Following", "Repository")
        const val HIDE_FAB = "false"
        const val EXTRA_GITHUBUSER = "extra_githubuser"
    }
}