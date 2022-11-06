package com.example.githubuser.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.API.Retrofit
import com.example.githubuser.API.reposUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposViewModel : ViewModel() {
    private val _listRepos = MutableLiveData<List<reposUser>>()
    val listRepos : LiveData<List<reposUser>> = _listRepos
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading
    
    fun queryRepos(userID : String) {
        _isLoading.value = true
        val client = Retrofit.client.getRepos(userID)
        client.enqueue(object : Callback<List<reposUser>> {
            override fun onResponse(call: Call<List<reposUser>>, response: Response<List<reposUser>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listRepos.value = response.body()
                }
            }
            
            override fun onFailure(call: Call<List<reposUser>>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "On Failure: ${t.message}")
            }
        })
    }
    
    companion object{
        private const val TAG = "ReposViewModel"
    }
}