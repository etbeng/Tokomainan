package com.example.githubuser

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    
    private lateinit var rvGithubUser: RecyclerView
    private var listGitHubUser : ArrayList<gitHubUser> = arrayListOf()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        supportActionBar?.title = "Github Users"
        rvGithubUser = findViewById(R.id.rv_ListUser)
        rvGithubUser.setHasFixedSize(true)
    
        listGitHubUser.addAll(DataGithub.listData)
        showRecyclerList()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menumain, menu)
        return super.onCreateOptionsMenu(menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnu_List -> { showRecyclerList() }
            R.id.about_page -> { showAboutMe() }
        }
        return super.onOptionsItemSelected(item)
    }
    
    private fun showAboutMe() {
        val intent = Intent(this, AboutMe::class.java )
        startActivity(intent)
    }
    
    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
           rvGithubUser.layoutManager = GridLayoutManager(this, 2)
        }
        else {
           rvGithubUser.layoutManager = LinearLayoutManager(this)
        }
        
        val adapter = ListGitHubAdapter(listGitHubUser)
        rvGithubUser.adapter = adapter
        adapter.itemDiKlik = { gitHubUser ->
            val intent = Intent(this, Detil_GithubUser::class.java)
            intent.putExtra(Detil_GithubUser.EXTRA_GITHUBUSER, gitHubUser)
            startActivity(intent)
        }
        
    }
}