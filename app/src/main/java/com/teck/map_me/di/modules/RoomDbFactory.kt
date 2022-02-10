package com.teck.map_me.di.modules

import android.content.Context
import androidx.room.Room
import com.teck.data.repositories.datasource.room.DataBase

object RoomDbFactory {
    private const val NAME_DB = "MapMeDb"
    fun createDb(context: Context) : DataBase = Room
        .databaseBuilder(context, DataBase::class.java, NAME_DB)
        .build()
}