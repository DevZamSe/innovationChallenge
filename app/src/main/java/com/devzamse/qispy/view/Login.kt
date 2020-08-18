package com.devzamse.qispy.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.devzamse.qispy.MapsActivity
import com.devzamse.qispy.R


class Login : AppCompatActivity() {

    private lateinit var number: EditText
    private lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        number = findViewById(R.id.number)
        pass = findViewById(R.id.pass)


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.i("permission", "done")
        } else {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION),11
                )
            }

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 11)
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 12)
            }
        }


    }

    fun clickLogin(view: View) {
        val i: Intent = Intent(this, MapsActivity::class.java)
        Log.e("usuario", "${number.text.toString()} pass ${pass.text.toString()}")
        startActivity(i)
        finish()
    }
}