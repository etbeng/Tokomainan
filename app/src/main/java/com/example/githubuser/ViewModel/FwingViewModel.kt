package com.example.githubuser.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.API.Retrofit
import com.example.githubuser.API.tableUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FwingViewModel : ViewModel() {
    private val _listFwing = MutableLiveData<List<tableUsers>>()
    val listFwing : LiveData<List<tableUsers>> = _listFwing
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading
    
    fun queryFollowing(userID : String) {
        _isLoading.value = true
        val client = Retrofit.client.getFollowers(userID, subid = "following")
        client.enqueue(object : Callback<List<tableUsers>> {
            override fun onResponse(call: Call<List<tableUsers>>, response: Response<List<tableUsers>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFwing.value = response.body()
                }
            }
            
            override fun onFailure(call: Call<List<tableUsers>>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "On Failure: ${t.message}")
            }
        })
    }
    
    companion object{
        private const val TAG = "FwingViewModel"
    }
    
}