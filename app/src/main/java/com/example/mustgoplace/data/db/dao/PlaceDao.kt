package com.example.mustgoplace.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mustgoplace.data.db.entity.PlaceEntity


@Dao
interface PlaceDao {

    @Query("SELECT * FROM place")
    suspend fun getPlace(): List<PlaceEntity>

}