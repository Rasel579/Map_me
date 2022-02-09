package com.teck.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.GoogleMap
import com.teck.domain.models.Place
import com.teck.ui.R
import com.teck.ui.databinding.FragmentMapBinding
import com.teck.ui.map.libs.adapters.InfoMarkerGoogleAdapter
import com.teck.ui.map.libs.Map
import com.teck.ui.map.libs.MapImpl
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.getKoin


class MapFragment : Fragment(R.layout.fragment_map) {
    private val viewBinding: FragmentMapBinding by viewBinding()
    private val scope = getKoin().createScope<MapFragment>()
    private val viewModel: MapViewModel = scope.get()
    private val infoMarkerGoogleAdapter: GoogleMap.InfoWindowAdapter by lazy {
        InfoMarkerGoogleAdapter(this.requireContext())
    }
    private val map: Map by lazy {
        MapImpl(childFragmentManager, infoMarkerGoogleAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.takeData().collect {
                showData(it)
            }
        }
    }

    private fun showData(places: List<Place>) {
        map.initUiSettings(R.id.google_map)
        map.addMarkers(places)
        initPermissions()
        map.initListeners(places)
    }

    private fun initPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            map.initMyLocation()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    map.initMyLocation()
                } else {
                    requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE
                    )
                }
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        fun newInstance() = MapFragment()
    }
}