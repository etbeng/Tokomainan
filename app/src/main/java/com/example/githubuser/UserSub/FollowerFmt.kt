package com.example.githubuser.UserSub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.ViewModel.FwerViewModel
import com.example.githubuser.Adapter.ReCycleAdapter
import com.example.githubuser.databinding.FragmentFollowerFmtBinding

class FollowerFmt : Fragment() {
    
    private var _binding : FragmentFollowerFmtBinding? = null
    private val binding get() = _binding!!
    private lateinit var FwerModel : FwerViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerFmtBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val userID = arguments?.getString(EXTRA_USERID) ?: ""
        //inisiasi
        FwerModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FwerViewModel::class.java)
        FwerModel.queryFollower(userID)
        FwerModel.listFwer.observe(viewLifecycleOwner) { user ->
            val adapter = ReCycleAdapter(user)
            binding.rvListFollowers.layoutManager = LinearLayoutManager(context)
            binding.rvListFollowers.adapter = adapter
            binding.rvListFollowers.setHasFixedSize(true)
        }
        FwerModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.pBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        const val EXTRA_USERID = "extraUserId"
    }
}