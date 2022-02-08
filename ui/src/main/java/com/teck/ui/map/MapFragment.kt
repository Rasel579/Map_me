package com.teck.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.teck.domain.models.Place
import com.teck.ui.R
import com.teck.ui.databinding.FragmentMapBinding
import org.koin.android.ext.android.getKoin


class MapFragment : Fragment(R.layout.fragment_map) {
    private val viewBinding: FragmentMapBinding by viewBinding()
    private val scope = getKoin().createScope<MapFragment>()
    private val viewModel: MapViewModel = scope.get()

    private val supportMapFragment: SupportMapFragment? by lazy {
        childFragmentManager
            .findFragmentById(R.id.google_map) as? SupportMapFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         val places = viewModel.getData()
        supportMapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap, places)
        }
    }

    private fun addMarkers(map: GoogleMap, places: List<Place>) {
        places.forEach { place ->
            map.addMarker(
                MarkerOptions().title(place.name)
                    .position(place.latLng)
            )
        }
    }

    companion object {
        fun newInstance() = MapFragment()
    }
}