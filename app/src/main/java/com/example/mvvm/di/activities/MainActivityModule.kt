package com.example.mvvm.di.activities

import com.example.mvvm.ui.popularmovies.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun providesMainActivity(): MainActivity
}

