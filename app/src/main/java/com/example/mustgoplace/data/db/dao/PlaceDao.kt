package com.example.mustgoplace.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mustgoplace.data.db.entity.PlaceEntity


@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(place: PlaceEntity): Long

    @Query("SELECT * FROM place")
    suspend fun getPlace(): List<PlaceEntity>



}