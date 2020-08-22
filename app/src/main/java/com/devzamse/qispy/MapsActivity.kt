package com.devzamse.qispy

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devzamse.qispy.view.Recarga
import com.devzamse.qispy.view.Scan
import com.devzamse.qispy.view.Splash
import com.getbase.floatingactionbutton.FloatingActionButton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONArray
import org.json.JSONException
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fire: FloatingActionButton
    private lateinit var qr: FloatingActionButton
    private lateinit var recarga: FloatingActionButton
    private lateinit var mProvider: HeatmapTileProvider
    private lateinit var mOverlay: TileOverlay
    var click = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fire = findViewById(R.id.fire)
        qr = findViewById(R.id.qr)
        recarga = findViewById(R.id.recarga)

        fire.setOnClickListener {
            click++
            if(click%2==0){
                addHeatMap()
            }else{
                mOverlay.remove()
            }
        }
        qr.setOnClickListener {
            IntentIntegrator(this).setBeepEnabled(true).setOrientationLocked(true).initiateScan()

        }
        recarga.setOnClickListener {
            val i: Intent = Intent(this, Recarga::class.java)
            startActivity(i)
        }

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


    private fun addHeatMap() {
        var list: List<LatLng?>? = null

        // Get the data: latitude/longitude positions of police stations.
        try {
            list = readItems(R.raw.police_stations)
        } catch (e: JSONException) {
            Toast.makeText(applicationContext!!, "Problem reading list of locations.", Toast.LENGTH_LONG)
                .show()
        }
        // Create the gradient.

        // Create the gradient.
        val colors = intArrayOf(
            Color.rgb(102, 225, 0),  // green
            Color.rgb(255, 0, 0) // red
        )

        val startPoints = floatArrayOf(
            0.2f, 1f
        )

        val gradient =
            Gradient(colors, startPoints)

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        mProvider = HeatmapTileProvider.Builder()
            .data(list)
            //.gradient(gradient)
            .radius(40)
            .build()

        // Add a tile overlay to the map, using the heat map tile provider.
        mOverlay = mMap.addTileOverlay(TileOverlayOptions().tileProvider(mProvider))
    }

    @Throws(JSONException::class)
    private fun readItems(resource: Int): List<LatLng?>? {
        val list = ArrayList<LatLng?>()
        val inputStream = resources.openRawResource(resource)
        val json = Scanner(inputStream).useDelimiter("\\A").next()
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            val `object` = array.getJSONObject(i)
            val lat = `object`.getDouble("lat")
            val lng = `object`.getDouble("lng")
            list.add(LatLng(lat, lng))
        }
        return list
    }


}