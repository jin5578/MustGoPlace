package com.example.mustgoplace.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mustgoplace.data.db.ApplicationDatabase.Companion.VERSION
import com.example.mustgoplace.data.db.dao.PlaceDao
import com.example.mustgoplace.data.db.dao.SearchDao
import com.example.mustgoplace.data.db.entity.PlaceEntity
import com.example.mustgoplace.data.db.entity.SearchEntity


@Database(
    entities = [
        PlaceEntity::class,
        SearchEntity::class
    ],
    version = VERSION,
    exportSchema = false
)

abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun placeDao(): PlaceDao
    abstract fun searchDao(): SearchDao

    companion object {
        const val VERSION = 1
        private const val TABLE_NAME = "must_go_db"

        @Volatile
        private var instance: ApplicationDatabase? = null

        fun getInstance(context: Context): ApplicationDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ApplicationDatabase {
            return Room.databaseBuilder(context, ApplicationDatabase::class.java, TABLE_NAME)
                .build()
        }
    }

}