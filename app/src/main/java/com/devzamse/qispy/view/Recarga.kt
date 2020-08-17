package com.devzamse.qispy.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.devzamse.qispy.R
import com.mercadopago.android.px.core.MercadoPagoCheckout.Builder


class Recarga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recarga)
    }

    fun mercadopago(view: View?) {
        Builder(
            "TEST-2b85324f-35e4-4ad6-a486-1135c621cfae",
            "565939008-fcef652f-81f2-475b-8638-44b4da4f4f4e"
        )
            .build()
            .startPayment(this, 1)
    }
}