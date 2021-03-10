package com.example.mvvm.di

import com.example.mvvm.MoviesApp
import com.example.mvvm.di.activities.MainActivityModule
import com.example.mvvm.di.modules.MovieModule
import com.example.mvvm.ui.popularmovies.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    MovieModule::class,
    MainActivityModule::class
])
interface MoviesAppComponent {

    fun inject(mainActivity: MainActivity)
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MoviesApp): Builder

        fun build(): MoviesAppComponent
    }

    fun inject(app: MoviesApp)

}