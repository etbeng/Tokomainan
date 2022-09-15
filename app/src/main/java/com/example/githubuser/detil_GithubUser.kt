package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class detil_GithubUser : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_github_user)
        
        //caption untuk masing2 activity
        supportActionBar?.title = "Github User Profile"
        
        val listGitUser = intent?.getParcelableExtra<gitHubUser>(EXTRA_GITHUBUSER)
        val imgPhoto : ImageView = findViewById(R.id.imgFotoDetil)
        val tvUid : TextView = findViewById(R.id.tvUserID)
        val tvUser : TextView = findViewById(R.id.tvUserDetil)
        val tvEmail : TextView = findViewById(R.id.tvEmail)
        val tvFlow : TextView = findViewById(R.id.tvFwerDetil)
        val tvFwing : TextView = findViewById(R.id.tvFwingDetil)
        val tvRepo : TextView = findViewById(R.id.tvRepoDetil)
        val tvPT : TextView = findViewById(R.id.tvPTDetil)
        val tvLokasi : TextView = findViewById(R.id.tvLokasiDetil)
    
        Glide.with(this)
            .load(listGitUser?.photo)
            .circleCrop()
            //.apply(RequestOptions().override(55,55))
            .into(imgPhoto)
        
        tvUid.text = listGitUser?.userID
        tvUser.text = listGitUser?.username
        tvEmail.text = listGitUser?.email
        tvFlow.text = listGitUser?.follower.toString() + " Followers"
        tvFwing.text = listGitUser?.follwing.toString() + " Following"
        tvRepo.text = listGitUser?.repository.toString() + " Repositories"
        tvPT.text = listGitUser?.company
        tvLokasi.text = listGitUser?.location
        
    }
    
    
    companion object {
        const val EXTRA_GITHUBUSER = "extra_githubuser"
    }
}