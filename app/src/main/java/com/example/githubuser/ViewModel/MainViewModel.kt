package com.example.githubuser.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.API.Retrofit
import com.example.githubuser.API.DbUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    
    private val _dbUsers = MutableLiveData<DbUsers>()
     val listGitUsers : LiveData<DbUsers> = _dbUsers
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading
    
    fun queryListGitUsers(query : String) {
        _isLoading.value = true
        val client = Retrofit.client.getUsers(query)
        client.enqueue(object : Callback<DbUsers> {
            override fun onResponse(call: Call<DbUsers>, response: Response<DbUsers>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _dbUsers.value = response.body()
                }
            }
            override fun onFailure(call: Call<DbUsers>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "On Failure: ${t.message}")
            }
        })
    }
    
    companion object{
        private const val TAG = "MainViewModel"
    }
}