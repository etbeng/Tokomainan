package com.example.githubuser.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RoomViewModel(aplikasi: Application) : ViewModel() {

    private val mRepo : FavoriteRepo = FavoriteRepo(aplikasi)
    
    fun getAllFavVM() : LiveData<List<TableFavorite>> = mRepo.getAllFavRepo()
    fun getSelected(login: String) : LiveData<TableFavorite> = mRepo.getSelectedRepo(login)
    
}