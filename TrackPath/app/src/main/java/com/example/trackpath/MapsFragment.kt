package com.example.trackpath

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.trackpath.UIFunctions.hide
import com.example.trackpath.UIFunctions.show
import com.example.trackpath.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsFragment : Fragment(),GoogleMap.OnMyLocationButtonClickListener{

    private var _binding: FragmentMapsBinding?=null
    private val binding get()=_binding!!
    private lateinit var map: GoogleMap

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map=googleMap
        map.isMyLocationEnabled=true
        map.setOnMyLocationButtonClickListener(this)
        map.uiSettings.apply {
            isZoomControlsEnabled=false
            isZoomGesturesEnabled=true
            isTiltGesturesEnabled=false

        }
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMapsBinding.inflate(inflater, container, false)

        binding.Start.setOnClickListener{}
        binding.Stop.setOnClickListener {}
        binding.Pause.setOnClickListener{}
        binding.Reset.setOnClickListener {}


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {             //To Avoid Memory Leaks
        super.onDestroyView()
        _binding=null
    }

    override fun onMyLocationButtonClick(): Boolean {

       lifecycleScope.launch{
           delay(2500)                               //New Code for delaying something
           binding.Start.show()
           binding.Stop.hide()
           binding.Pause.hide()
           binding.Reset.hide()
       }
        return false




    }
}