package com.example.githubuser.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Entity
@Parcelize
data class TableFavorite (
    @PrimaryKey
    @ColumnInfo(name = "login")
    var userID: String = "",
    
    @ColumnInfo(name = "avatar")
    var avatar: String? = null,
    
): Parcelable
