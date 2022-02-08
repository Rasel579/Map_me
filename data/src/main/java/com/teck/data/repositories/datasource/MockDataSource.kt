package com.teck.data.repositories.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teck.domain.models.Place
import com.teck.domain.models.PlaceResponse
import com.teck.domain.models.toPlace
import java.io.InputStream
import java.io.InputStreamReader

class MockDataSource (private val context: Context, private val resources: Int): DataSource {
    private val gson: Gson
          get() = Gson()
    private val inputStream: InputStream
          get() = context.resources.openRawResource(resources)
    private fun read() : List<Place>{
        val itemType = object : TypeToken<List<PlaceResponse>>(){}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
    override fun getData(): List<Place> = read()
}