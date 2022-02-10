package com.teck.data.repositories.datasource.store

import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teck.data.repositories.datasource.DataSource
import com.teck.data.repositories.datasource.room.EntityDao
import com.teck.domain.models.Place
import com.teck.domain.models.entities.PlaceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LocalStoreSourceImpl(
    private val database: EntityDao
) : DataSource {
    override fun getData(): Flow<List<Place>> =
        database
            .getALl()
            .map {
                    mapToPlace(it)
            }.flowOn(Dispatchers.IO)

    override suspend fun saveData(place: Place) {
        database.insert(mapToPlaceEntity(place))
    }

    override suspend fun update(place: Place) {
        database.update(mapToPlaceEntity(place))
    }

    override suspend fun delete(place: Place) {
        database.delete(mapToPlaceEntity(place))
    }

    private fun mapToPlace(entities: List<PlaceEntity>) =
        entities.map{ placeEntity ->
            Place(
                id =  placeEntity.id,
                name = placeEntity.name,
                latLng = deserializeToLatLng(placeEntity.latLng),
                address = placeEntity.address,
                rating = placeEntity.rating
            )
        }


    private fun deserializeToLatLng(latLng: String): LatLng = Gson().fromJson<LatLng>(
          latLng,
          object : TypeToken<LatLng>(){}.type
      )


    private fun mapToPlaceEntity(place: Place): PlaceEntity  =
        PlaceEntity(
            name = place.name,
            latLng = serializesToString(place.latLng),
            address = place.address,
            rating = place.rating,
            id = place.id
        )

    private fun serializesToString(latLng: LatLng): String  = Gson().toJson(
        latLng,
        object :TypeToken<LatLng>(){}.type
    )

}