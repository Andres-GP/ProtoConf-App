package com.proto.conf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import com.proto.conf.R
import com.proto.conf.model.Ubication

/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
       // Manipula el mapa en cualquier momento
    override fun onMapReady(googleMap: GoogleMap?) {

           val ubication = Ubication()
           val zoom = 16f
           val centerMap = LatLng(ubication.latitude,ubication.longitude)

           googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))

           val centerMark = LatLng(ubication.latitude, ubication.longitude)
           val markerOptions = MarkerOptions()
           markerOptions.position(centerMark)
           markerOptions.title("Proto Conf")

           val bitmapDraw = context?.applicationContext.let { view?.context?.let { it1 ->
               ContextCompat.getDrawable(
                   it1,R.drawable.protmpany_logo_map)
           } } as BitmapDrawable
           val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 100, 100, false)
           markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
           googleMap?.addMarker(markerOptions)

           googleMap?.setOnMarkerClickListener(this)

           googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))


    }

    override fun onMarkerClick(p0: Marker?): Boolean {
            findNavController().navigate(R.id.UbicationDetailFragmentDialog)
            return true
    }

}
