package com.teck.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.GoogleMap
import com.teck.domain.models.Place
import com.teck.ui.R
import com.teck.ui.databinding.FragmentMapBinding
import com.teck.ui.map.libs.Map
import com.teck.ui.map.libs.MapImpl
import com.teck.ui.map.libs.ViewListener
import com.teck.ui.map.libs.adapters.InfoMarkerGoogleAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin


class MapFragment : Fragment(R.layout.fragment_map), ViewListener {
    private val viewBinding: FragmentMapBinding by viewBinding()
    private val scope = getKoin().createScope<MapFragment>()
    private val viewModel: MapViewModel = scope.get()
    private val infoMarkerGoogleAdapter: GoogleMap.InfoWindowAdapter by lazy {
        InfoMarkerGoogleAdapter(this.requireContext())
    }
    private val map: Map by lazy {
        MapImpl(
            fragmentManager = childFragmentManager,
            infoMarkerGoogleAdapter = infoMarkerGoogleAdapter,
            viewListener = this,
            geocoder = Geocoder(this.requireContext())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        map.initUiSettings(R.id.google_map)
        initPermissions()
        map.onMapListener()

    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.takeData().collect {
                Log.e("take data", it.toString())
                showData(it)
            }
        }
    }

    private fun showData(places: List<Place>) {
        map.initUiSettings(R.id.google_map)
        map.addMarkers(places)
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


    override fun saveData(place: Place) {
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            try {
                viewModel.saveData(place)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
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