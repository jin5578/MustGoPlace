package com.example.mustgoplace.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "place")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "store_name")
    val storeName: String,
    @ColumnInfo(name = "latitude")
    val lat: String,
    @ColumnInfo(name = "longitude")
    val lng: Double
)
