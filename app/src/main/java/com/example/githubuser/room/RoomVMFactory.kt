package com.example.githubuser.room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomVMFactory private constructor(private val aplikasi: Application) :
    ViewModelProvider.NewInstanceFactory() {
    
    companion object {
        @Volatile
        private var INSTANCE : RoomVMFactory? = null
        
        @JvmStatic
        fun getInstance(aplikasi: Application) : RoomVMFactory {
            if (INSTANCE == null) {
                synchronized(RoomVMFactory::class.java) {
                    INSTANCE = RoomVMFactory(aplikasi)
                }
            }
            return INSTANCE as RoomVMFactory
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel> create (modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(aplikasi) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(aplikasi) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}