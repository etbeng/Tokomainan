package com.example.githubuser.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface IDataDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tabel : TableFavorite)
    
    @Update
    fun update(tabel : TableFavorite)
    
    @Delete
    fun delete(tabel: TableFavorite)
    
    @Query("SELECT * FROM TableFavorite ORDER BY login")
    fun getAll(): LiveData<List<TableFavorite>>
    
    @Query("SELECT * FROM TableFavorite WHERE login = :login LIMIT 1")
    fun getSelected(login: String): LiveData<TableFavorite?>
}
