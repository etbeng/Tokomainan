package com.example.githubuser

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubuser.API.DetilUser
import com.example.githubuser.Adapter.PagerAdapter
import com.example.githubuser.ViewModel.DetilViewModel
import com.example.githubuser.databinding.ActivityDetilGithubUserBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetilGithubUser : AppCompatActivity() {
    
    private lateinit var binding : ActivityDetilGithubUserBinding
    private val detilModel by viewModels<DetilViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityDetilGithubUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //observe query detiluser dan loading
        val userID = intent?.getStringExtra(EXTRA_GITHUBUSER) ?: ""
        detilModel.queryDetilUser(userID)
        detilModel.gitUser.observe(this) { user ->
            displayDetilUsers(user)
        }
        detilModel.isLoading.observe(this) {
            showLoading(it)
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
    
    companion object {
        private val TAB_TITLES = arrayListOf("Followers", "Following", "Repository")
        const val EXTRA_GITHUBUSER = "extra_githubuser"
    }
}