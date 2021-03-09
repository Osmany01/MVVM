package com.example.mvvm.data.db

import com.example.mvvm.data.domain.LocalDataSource
import com.example.mvvm.data.domain.MovieModel
import com.example.mvvm.data.toDomainMovie
import com.example.mvvm.data.toRoomMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(dataBase: MovieDatabase) : LocalDataSource {

    private val movieDao = dataBase.movieDao()

    override suspend fun size(): Int = movieDao.movieCount()

    override suspend fun saveMovies(movies: List<MovieModel>) {

        movieDao.insertMovies(movies.map {
            it.toRoomMovie()
        })
    }

    override fun getMovies(): Flow<List<MovieModel>> =
        movieDao.getAll()
            .map { moviesDB ->
                moviesDB.map {
                    it.toDomainMovie()
                }
            }
}