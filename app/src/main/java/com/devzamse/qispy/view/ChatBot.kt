package com.devzamse.qispy.view

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.devzamse.qispy.R


class ChatBot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        val wb: WebView = findViewById(R.id.webView)
        wb.getSettings().setJavaScriptEnabled(true)
        wb.getSettings().setLoadWithOverviewMode(true)
        wb.getSettings().setUseWideViewPort(true)
        wb.getSettings().setBuiltInZoomControls(true)
        wb.getSettings().setPluginState(WebSettings.PluginState.ON)
        wb.loadUrl("https://web-chat.global.assistant.watson.cloud.ibm.com/preview.html?" +
                "region=us-south&integrationID=10493140-6783-4f72-8706-f2dba8f8d34e&" +
                "serviceInstanceID=0e1da882-a1c2-45ab-86c2-993eedf03921\n")
    }
}