package com.devzamse.qispy

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.devzamse.qispy.view.Recarga
import com.devzamse.qispy.view.Scan
import com.devzamse.qispy.view.Splash
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.zxing.integration.android.IntentIntegrator


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = true

        val sydney = LatLng(-12.068516, -76.937517)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17f))
        val marker = mMap.addMarker(MarkerOptions().position(sydney).title("Información del bus"))
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bus))
        mMap.setOnMarkerClickListener {
            val intent = Intent(this@MapsActivity, Splash::class.java)
            startActivity(intent)
            false
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMarkerClickListener {
            Handler().postDelayed({
                val intent = Intent(this, Scan::class.java)
                startActivity(intent)
                false
            },250)
        }
    }

    fun scan(view: View) {
        IntentIntegrator(this).setBeepEnabled(true).setOrientationLocked(true).initiateScan()
    }

    fun recarga(view: View) {
        val i: Intent = Intent(this, Recarga::class.java)
        startActivity(i)
    }


    @SuppressLint("ResourceType")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Log.e("Nulo","true")
            } else {
                val i: Intent = Intent(this, Scan::class.java)
                startActivity(i)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}