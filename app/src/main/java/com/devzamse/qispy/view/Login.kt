package com.devzamse.qispy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
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

    }

    fun clickLogin(view: View) {
        val i: Intent = Intent(this, MapsActivity::class.java)
        Log.e("usuario", "${number.text.toString()} pass ${pass.text.toString()}")
        startActivity(i)
        finish()
    }
}