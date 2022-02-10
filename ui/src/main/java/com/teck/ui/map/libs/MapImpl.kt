package com.teck.ui.map.libs

import android.location.Address
import android.location.Geocoder
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.teck.domain.models.Place

class MapImpl(
    private val fragmentManager: FragmentManager,
    private val infoMarkerGoogleAdapter: GoogleMap.InfoWindowAdapter,
    private val viewListener: ViewListener,
    private val geocoder: Geocoder
) : Map {
    private var supportMapFragment: SupportMapFragment? = null
    override fun initUiSettings(@IdRes id: Int) {
        supportMapFragment = fragmentManager.findFragmentById(id) as? SupportMapFragment
        supportMapFragment?.getMapAsync { googleMap ->
            googleMap.uiSettings.isCompassEnabled = true
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.setInfoWindowAdapter(infoMarkerGoogleAdapter)
        }
    }

    override fun addMarkers(placeData: List<Place>) {
        supportMapFragment?.getMapAsync { googleMap ->
            placeData.forEach { place ->
                val marker = googleMap.addMarker(
                    MarkerOptions().title(place.name)
                        .position(place.latLng)
                )
                marker?.tag = place
            }
        }
    }

    override fun initMyLocation() {
        supportMapFragment?.getMapAsync { googleMap ->
            googleMap.isMyLocationEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = true

        }
    }

    override fun initListeners(placeData: List<Place>) {
        supportMapFragment?.getMapAsync { googleMap ->
            googleMap.setOnMapLoadedCallback {
                if (placeData.isNotEmpty()) {
                    val bounds = LatLngBounds.builder()
                    placeData.forEach { bounds.include(it.latLng) }
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
                }
            }
        }
    }

    override fun onMapListener() {
        supportMapFragment?.getMapAsync { googleMap ->
            googleMap.setOnMapLongClickListener {
                val location = fetchLocation(it)
                if (location.isNotEmpty()) {
                    viewListener.saveData(
                        Place(
                            id = randomId,
                            name = location.first().featureName ?: DEFAULT_NULLABLE_RESPONSE_GEOCODER,
                            latLng = it,
                            address = location.first().thoroughfare ?: DEFAULT_NULLABLE_RESPONSE_GEOCODER,
                            rating = DEFAULT_RATING_NEW_MARKER_PLACE
                        )
                    )
                }
                val marker = MarkerOptions()
                    .position(it)
                googleMap.addMarker(marker)
            }
        }
    }

    private fun fetchLocation(latLng: LatLng): List<Address> = geocoder.getFromLocation(
        latLng.latitude,
        latLng.longitude,
        MAX_RESULTS_RESPONSE_LOCATION
    )

    companion object {
        private val randomId = (Math.random() * 1000).toInt()
        private const val MAX_RESULTS_RESPONSE_LOCATION = 1
        private const val DEFAULT_RATING_NEW_MARKER_PLACE = 0f
        private const val DEFAULT_NULLABLE_RESPONSE_GEOCODER = "Unknown Place"
    }


}