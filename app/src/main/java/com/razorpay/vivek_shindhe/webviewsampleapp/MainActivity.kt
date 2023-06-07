package com.razorpay.vivek_shindhe.webviewsampleapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webview = findViewById<WebView>(R.id.webview)
        val etUrl = findViewById<EditText>(R.id.et_url)
        val btnLoad = findViewById<Button>(R.id.btn_load)
        webview.settings.javaScriptEnabled = true
        webview.settings.allowContentAccess = true
        webview.webViewClient = MyWebViewClient(this)
        btnLoad.setOnClickListener {
            webview.loadUrl(etUrl.text.toString())

        }


    }

    class MyWebViewClient(private val activity: Activity): WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request!!.url.toString()
            if (!url.startsWith("https") || !url.startsWith("http")){
                try {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse(request.url.toString()))
                    activity.startActivityForResult(i, 2001)
                } catch (e: ActivityNotFoundException) {

                }
            }
            return true

        }
    }
}