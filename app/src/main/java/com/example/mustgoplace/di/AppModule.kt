package com.example.mustgoplace.di

import android.app.Application
import android.content.Context
import com.example.mustgoplace.MustGoPlaceApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun providesContext(app: MustGoPlaceApp): Context = app.applicationContext

    @Singleton
    @Provides
    fun providesApplication(): Application = MustGoPlaceApp()

}