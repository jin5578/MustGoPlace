package com.example.mustgoplace.data.repository

import com.example.mustgoplace.data.db.dao.PlaceDao
import com.example.mustgoplace.data.db.entity.mapper.mapEntity
import com.example.mustgoplace.domain.PlaceRepository
import com.example.mustgoplace.model.Place


class PlaceRepositoryImpl(
    private val placeDao: PlaceDao
) : PlaceRepository {
    override suspend fun insert(place: Place) {
        placeDao.insert(place.mapEntity())
    }
}