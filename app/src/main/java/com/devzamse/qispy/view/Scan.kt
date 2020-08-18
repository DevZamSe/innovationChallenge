package com.devzamse.qispy.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.devzamse.qispy.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class Scan : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        progressBar = findViewById(R.id.progressBar)
        progressBar.progress = 50

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-12.068516, -76.937517)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17f))
        val marker = mMap.addMarker(MarkerOptions().position(sydney).title("Información del bus"))
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bus))
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    fun recarga(view: View?) {
        val i: Intent = Intent(this, Recarga::class.java)
        startActivity(i)
    }
}