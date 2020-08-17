package com.devzamse.qispy.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.devzamse.qispy.R
import com.google.zxing.integration.android.IntentIntegrator


class Scan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        val integrator = IntentIntegrator(this)
        integrator.initiateScan()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        val scanResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
        if (scanResult != null) {
            val re = scanResult.contents
            Log.d("code", re)
        }
        // else continue with any other code you need in the method
    }
}