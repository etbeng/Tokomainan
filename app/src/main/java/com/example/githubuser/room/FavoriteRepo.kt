package com.example.githubuser.room

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepo(aplikasi: Application) {
    private var mDataDAO : IDataDAO
    private val exeService : ExecutorService = Executors.newSingleThreadExecutor()
    
    init {
        val db = FavoriteDB.getDB(aplikasi)
        mDataDAO = db.DataDAO()
    }
    
    fun getAllFavRepo(): LiveData<List<TableFavorite>> = mDataDAO.getAll()
    fun getSelectedRepo(login: String) : LiveData<TableFavorite?> = mDataDAO.getSelected(login)
    
    fun insertRepo(tabel : TableFavorite) {
        exeService.execute {mDataDAO.insert(tabel)}
    }
    
    fun deleteRepo(tabel : TableFavorite) {
        exeService.execute {mDataDAO.delete(tabel)}
    }
}