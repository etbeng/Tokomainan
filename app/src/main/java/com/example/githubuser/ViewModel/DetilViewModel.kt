package com.example.githubuser.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.API.Retrofit
import com.example.githubuser.API.DetilUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetilViewModel : ViewModel() {
    private val _detilUser = MutableLiveData<DetilUser>()
    val gitUser : LiveData<DetilUser> = _detilUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading
    
    fun queryDetilUser(userID: String) {
        _isLoading.value = true
        val client = Retrofit.client.getDetailUser(userID)
        client.enqueue(object : Callback<DetilUser> {
            override fun onResponse(call: Call<DetilUser>, response: Response<DetilUser>) {
                if (response.isSuccessful) {
                   _isLoading.value = false
                   _detilUser.value = response.body()
                }
            }
        
            override fun onFailure(call: Call<DetilUser>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "On Failure: ${t.message}")
            }
        })
    }
    
    companion object{
        private const val TAG = "detilViewModel"
    }
}