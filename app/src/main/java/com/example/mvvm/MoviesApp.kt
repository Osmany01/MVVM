package com.example.mvvm

import android.app.Application
import androidx.room.Room
import com.example.mvvm.data.db.MovieDatabase
import com.example.mvvm.di.DaggerMoviesAppComponent
import com.example.mvvm.di.MoviesAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MoviesApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var moviesAppComponent: MoviesAppComponent

    lateinit var db: MovieDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            MovieDatabase::class.java, "movie-db"
        ).build()

        moviesAppComponent = DaggerMoviesAppComponent.builder()
            .application(this)
            .build()

        moviesAppComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}


