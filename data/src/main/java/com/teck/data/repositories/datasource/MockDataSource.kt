package com.teck.data.repositories.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teck.domain.models.Place
import com.teck.domain.models.PlaceResponse
import com.teck.domain.models.toPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.InputStream
import java.io.InputStreamReader

class MockDataSource (private val context: Context, private val resources: Int): DataSource {
    private val gson: Gson
          get() = Gson()
    private val inputStream: InputStream
          get() = context.resources.openRawResource(resources)
    private fun read() : Flow<List<Place>> = flow {
        val itemType = object : TypeToken<List<PlaceResponse>>(){}.type
        val reader = InputStreamReader(inputStream)
        emit(gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        })
    }.flowOn(Dispatchers.IO)
    override fun getData(): Flow<List<Place>> = read()
}