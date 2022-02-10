package com.teck.data.repositories.datasource.room

import androidx.room.*
import com.teck.domain.models.entities.PlaceEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface EntityDao {
    @Query("SELECT * FROM PlaceEntity")
    fun getALl() : Flow<List<PlaceEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(placeEntity: PlaceEntity)
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(placeEntity: PlaceEntity)
    @Delete
    fun delete(placeEntity: PlaceEntity)
}