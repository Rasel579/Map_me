package com.teck.data.repositories.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teck.domain.models.entities.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
     abstract fun entityDao(): EntityDao
}