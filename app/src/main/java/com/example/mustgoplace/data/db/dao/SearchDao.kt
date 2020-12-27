package com.example.mustgoplace.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mustgoplace.data.db.entity.SearchEntity


@Dao
interface SearchDao {

    @Query("SELECT * FROM search")
    suspend fun getProfile(): SearchEntity

}