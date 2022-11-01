package com.example.githubuser.UserSub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.Adapter.ReposCycleAdapter
import com.example.githubuser.ViewModel.ReposViewModel
import com.example.githubuser.databinding.FragmentRepoBinding


class RepoFragment : Fragment() {
    private var _binding : FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private lateinit var ReposModel : ReposViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRepoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val userID = arguments?.getString(EXTRA_USERID) ?: ""
        
        ReposModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ReposViewModel::class.java)
        ReposModel.queryRepos(userID)
        ReposModel.listRepos.observe(viewLifecycleOwner) { user ->
            val adapter = ReposCycleAdapter(user)
            binding.apply {
                rvListRepos.layoutManager = LinearLayoutManager(context)
                rvListRepos.adapter = adapter
                rvListRepos.setHasFixedSize(true)
            }
        }
        ReposModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pBar.visibility = View.VISIBLE
        } else {
            binding.pBar.visibility = View.GONE
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val EXTRA_USERID = "extraUserId"
    }
}