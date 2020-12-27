package com.example.mustgoplace.data.db

import android.content.Context
import com.example.mustgoplace.data.db.dao.PlaceDao
import com.example.mustgoplace.data.db.dao.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesApplicationDatabase(
        @ApplicationContext context: Context
    ): ApplicationDatabase = ApplicationDatabase.getInstance(context)

    @Singleton
    @Provides
    fun providesPlaceDao(
        db: ApplicationDatabase
    ): PlaceDao = db.placeDao()

    @Singleton
    @Provides
    fun providesSearchDao(
        db: ApplicationDatabase
    ): SearchDao = db.searchDao()

}