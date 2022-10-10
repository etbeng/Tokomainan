package com.example.githubuser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.API.*
import com.example.githubuser.Adapter.ReCycleAdapter
import com.example.githubuser.ViewModel.MainViewModel
import com.example.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var rvGithubUser: RecyclerView
    private lateinit var binding : ActivityMainBinding
    private val listGithubUser by viewModels<MainViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportActionBar?.title = "Github Users"
        rvGithubUser = findViewById(R.id.rv_ListUser)
        rvGithubUser.setHasFixedSize(true)
        
        listGithubUser.listGitUsers.observe( this, { user ->
            showRecyclerList(user)
        })
        listGithubUser.isLoading.observe( this, {
            showLoading(it)
        })
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menumain, menu)
        
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Masukkan nama user"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    listGithubUser.queryListGitUsers(query)
                    searchView.clearFocus()
                } else {
                    Toast.makeText(this@MainActivity, "Pencarian data tidak ditemukan !", Toast.LENGTH_SHORT).show()
                }
                return true
            }
            
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                showAboutMe()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    
    private fun showAboutMe() {
        val intent = Intent(this, AboutMe::class.java)
        startActivity(intent)
    }
    
    private fun showRecyclerList(listUser : DbUsers) {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvGithubUser.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvGithubUser.layoutManager = LinearLayoutManager(this)
        }
        
        val adapter = ReCycleAdapter(listUser.items)
        rvGithubUser.adapter = adapter
        adapter.itemDiKlik = { gitHubUser ->
            val intent = Intent(this@MainActivity, DetilGithubUser::class.java)
            intent.putExtra(DetilGithubUser.EXTRA_GITHUBUSER, gitHubUser.userID)
            startActivity(intent)
        }
    }
    
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pBar.visibility = View.VISIBLE
        } else {
            binding.pBar.visibility = View.GONE
        }
    }
    
}