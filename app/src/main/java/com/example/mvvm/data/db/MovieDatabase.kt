package com.example.mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDB::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}