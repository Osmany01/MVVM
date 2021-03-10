package com.example.mvvm.di.modules

import android.app.Application
import androidx.room.RoomDatabase
import com.example.mvvm.MoviesApp
import com.example.mvvm.data.db.MovieDatabase
import com.example.mvvm.data.db.RoomDataSource
import com.example.mvvm.data.domain.LocalDataSource
import com.example.mvvm.data.domain.MoviesRepository
import com.example.mvvm.data.domain.RemoteDataSource
import com.example.mvvm.data.server.TheMovieDBDataSource
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @Provides
    fun providesMovieDataBase(application: MoviesApp): MovieDatabase =
        application.db

    @Provides
    fun providesLocalDataSource(database: MovieDatabase): LocalDataSource =
         RoomDataSource(database)

    @Provides
    fun providesRemoteDataSource(): RemoteDataSource =
        TheMovieDBDataSource()

    @Provides
    fun providesMoviesRepository(localDataSource: LocalDataSource,
                                 remoteDataSource: RemoteDataSource): MoviesRepository =
        MoviesRepository(localDataSource, remoteDataSource)
}