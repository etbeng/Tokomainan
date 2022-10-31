package com.example.githubuser.room

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.githubuser.API.tableUsers

class FavoriteViewModel(aplikasi: Application) : ViewModel() {
    private val mRepo : FavoriteRepo = FavoriteRepo(aplikasi)
    
    fun insertVM(tabel : TableFavorite) {
        mRepo.insertRepo(tabel)
    }

    fun updateVM(tabel: TableFavorite) {
        mRepo.updateRepo(tabel)
    }
    
    fun deleteVM(tabel: TableFavorite) {
        mRepo.deleteRepo(tabel)
    }
    
}