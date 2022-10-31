package com.example.githubuser.room

import androidx.recyclerview.widget.DiffUtil

class RoomDiffCallBack(private val mOldTable: List<TableFavorite>, private val mNewTable: List<TableFavorite>) : DiffUtil.Callback() {
    
    override fun getOldListSize(): Int {
        return mOldTable.size
    }
    
    override fun getNewListSize(): Int {
        return mNewTable.size
    }
    
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldTable[oldItemPosition].userID == mNewTable[newItemPosition].userID
    }
    
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPos = mOldTable[oldItemPosition]
        val newPos = mNewTable[newItemPosition]
        return oldPos.avatar == newPos.avatar
    }
}