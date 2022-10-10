package com.example.githubuser.UserSub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.ViewModel.FwingViewModel
import com.example.githubuser.Adapter.ReCycleAdapter
import com.example.githubuser.databinding.FragmentFollowingFmtBinding


class FollowingFmt : Fragment() {
    private var _binding : FragmentFollowingFmtBinding? = null
    private val binding get() = _binding!!
    private lateinit var FwingModel : FwingViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingFmtBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val userID = arguments?.getString(FollowerFmt.EXTRA_USERID) ?: ""
    
        FwingModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FwingViewModel::class.java)
        FwingModel.queryFollowing(userID)
        FwingModel.listFwing.observe(viewLifecycleOwner) { user ->
            val adapter = ReCycleAdapter(user)
            binding.rvListFollowing.layoutManager = LinearLayoutManager(context)
            binding.rvListFollowing.adapter = adapter
            binding.rvListFollowing.setHasFixedSize(true)
        }
        FwingModel.isLoading.observe(viewLifecycleOwner) {
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