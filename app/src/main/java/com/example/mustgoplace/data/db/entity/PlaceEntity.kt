package com.example.mustgoplace.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "place")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "visitedAt")
    val visitedAt: Date,
    @ColumnInfo(name = "writtenAt")
    val writtenAt: Date
)
