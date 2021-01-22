package com.example.mustgoplace.domain

import com.example.mustgoplace.model.Place


interface PlaceRepository {
    suspend fun insert(place: Place)
}