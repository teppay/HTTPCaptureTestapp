package com.example.httpcapturetestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    lateinit var client: OkHttpClient
    lateinit var pinnedClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initHTTPClients()

        val cards = mutableListOf<View>()

        val ll = findViewById<LinearLayout>(R.id.linearlayout)
        val card1 = LayoutInflater.from(applicationContext).inflate(R.layout.mycard, ll, false)
        card1.apply {
            findViewById<TextView>(R.id.cardTitle).text = resources.getString(R.string.http_title)
            findViewById<TextView>(R.id.cardSubTitle).text = resources.getString(R.string.http_url)

            val resultText = findViewById<TextView>(R.id.resultText)

            findViewById<Button>(R.id.button_send).setOnClickListener {
                getAndSetText(resources.getString(R.string.http_url), resultText, client)
            }
        }
        cards.add(card1)

        val card2 = LayoutInflater.from(applicationContext).inflate(R.layout.mycard, ll, false).apply {
            findViewById<TextView>(R.id.cardTitle).text = resources.getString(R.string.https_title)
            findViewById<TextView>(R.id.cardSubTitle).text = resources.getString(R.string.https_url)

            val resultText = findViewById<TextView>(R.id.resultText)

            findViewById<Button>(R.id.button_send).setOnClickListener {
                getAndSetText(resources.getString(R.string.https_url), resultText, client)
            }
        }
        cards.add(card2)

        val card3 = LayoutInflater.from(applicationContext).inflate(R.layout.mycard, ll, false).apply {
            findViewById<TextView>(R.id.cardTitle).text = resources.getString(R.string.https_pinning_title)
            findViewById<TextView>(R.id.cardSubTitle).text = resources.getString(R.string.https_pinning_url)

            val resultText = findViewById<TextView>(R.id.resultText)

            findViewById<Button>(R.id.button_send).setOnClickListener {
                getAndSetText(resources.getString(R.string.https_pinning_url), resultText, pinnedClient)
            }
        }
        cards.add(card3)

        cards.forEach { card ->
            card.apply {
                findViewById<Button>(R.id.button_reset).setOnClickListener {
                    findViewById<TextView>(R.id.resultText).text = ""
                }
            }
            ll.addView(card)
        }
    }

    fun initHTTPClients() {
        this.client = OkHttpClient()
        val pinner = CertificatePinner.Builder()
            .add(resources.getString(R.string.https_pinning_url), resources.getString(R.string.https_pinning_hash))
            .build()
        this.pinnedClient = OkHttpClient.Builder()
            .certificatePinner(pinner)
            .build()
    }

    fun getAndSetText(url: String, textView: TextView, client: OkHttpClient) {
        var request = Request.Builder()
            .url(url)
            .get()
            .build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val resString = response.body()?.string()
                textView.text = resString
            }
        })
    }
}