package com.example.githubuser.Adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.UserSub.FollowerFmt
import com.example.githubuser.UserSub.FollowingFmt
import com.example.githubuser.UserSub.RepoFragment

class PagerAdapter(activity: AppCompatActivity, private val userID: String) : FragmentStateAdapter(activity) {
    
    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        val fragment = when (position) {
            0 -> {
                bundle.putString(FollowerFmt.EXTRA_USERID, userID)
                FollowerFmt()
            }
            1 -> {
                bundle.putString(FollowingFmt.EXTRA_USERID, userID)
                FollowingFmt()
            }
            else -> {
                bundle.putString(RepoFragment.EXTRA_USERID, userID)
                RepoFragment()
            }
        }
        fragment.arguments = bundle
        return fragment
    }
    
    override fun getItemCount(): Int {
        return 3
    }
}