package com.example.githubuser.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TableFavorite::class], version = 1)
abstract class FavoriteDB : RoomDatabase() {
    
    abstract fun DataDAO() : IDataDAO
    
    companion object {
        @Volatile
        private var INSTANCE : FavoriteDB? = null
        
        @JvmStatic
        fun getDB(context: Context) : FavoriteDB {
            if (INSTANCE == null) {
               synchronized(TableFavorite::class.java) {
                   INSTANCE = Room.databaseBuilder(context.applicationContext,
                              FavoriteDB::class.java, "FavoriteDB").build()
               }
            }
            return INSTANCE as FavoriteDB
        }
    }
}