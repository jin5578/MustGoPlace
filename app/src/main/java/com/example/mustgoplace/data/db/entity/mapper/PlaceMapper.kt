package com.example.mustgoplace.data.db.entity.mapper

import com.example.mustgoplace.data.db.entity.PlaceEntity
import com.example.mustgoplace.model.Place


fun Place.mapEntity(): PlaceEntity {
    return PlaceEntity(id, content, visitedAt, writtenAt)
}