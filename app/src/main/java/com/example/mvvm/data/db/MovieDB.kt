package com.example.mvvm.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MovieDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imageUrl: String,
    val title: String,
    var isFavorite: Boolean = false
)