package com.example.mustgoplace.data.repository

import com.example.mustgoplace.data.db.dao.PlaceDao
import com.example.mustgoplace.domain.PlaceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesPlaceRepository(
        dao: PlaceDao
    ): PlaceRepository = PlaceRepositoryImpl(dao)
}