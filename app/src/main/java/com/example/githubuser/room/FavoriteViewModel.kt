package com.example.githubuser.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel(aplikasi: Application) : ViewModel() {
    private val mRepo : FavoriteRepo = FavoriteRepo(aplikasi)
    
    fun insertVM(tabel : TableFavorite) {
        mRepo.insertRepo(tabel)
    }

    fun deleteVM(tabel: TableFavorite) {
        mRepo.deleteRepo(tabel)
    }
    fun getSelectedVM(login: String) : LiveData<TableFavorite?> = mRepo.getSelectedRepo(login)
}