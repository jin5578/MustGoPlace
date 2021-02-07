package com.example.mustgoplace.data.db.entity.mapper

import com.example.mustgoplace.data.db.entity.PlaceEntity
import com.example.mustgoplace.model.Place


fun PlaceEntity.mapPlace(): Place = Place(id, content, visitedAt, writtenAt)

fun Place.mapEntity(): PlaceEntity = PlaceEntity(id, content, visitedAt, writtenAt)

fun List<PlaceEntity>.mapPlaceList(): List<Place> = map { it.mapPlace() }

