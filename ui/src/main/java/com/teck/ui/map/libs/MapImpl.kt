package com.teck.ui.map.libs

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.teck.domain.models.Place

class MapImpl(
    private val fragmentManager: FragmentManager,
    private val infoMarkerGoogleAdapter: GoogleMap.InfoWindowAdapter,
    private val viewListener: ViewListener
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
                val bounds = LatLngBounds.builder()
                placeData.forEach { bounds.include(it.latLng) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }
            googleMap.setOnMapLongClickListener {
                viewListener.saveData(
                    Place(
                        name = "new",
                        latLng = it,
                        address = "new address",
                        rating = 0f
                    )
                )
                googleMap.addMarker(
                    MarkerOptions()
                        .position(it)
                )
            }
        }
    }


}