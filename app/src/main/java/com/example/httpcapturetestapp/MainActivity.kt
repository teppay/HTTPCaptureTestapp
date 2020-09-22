package com.example.httpcapturetestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    val client: OkHttpClient
    init {
        client = OkHttpClient()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ll = findViewById<LinearLayout>(R.id.linearlayout)
        val card1 = LayoutInflater.from(applicationContext).inflate(R.layout.mycard, ll, false)
        card1.apply {
            findViewById<TextView>(R.id.cardTitle).text = resources.getString(R.string.http_title)
            findViewById<TextView>(R.id.cardSubTitle).text = resources.getString(R.string.http_url)

            val resultText = findViewById<TextView>(R.id.resultText)

            findViewById<Button>(R.id.button).setOnClickListener {
                getAndSetText(resources.getString(R.string.http_url), resultText)
            }
        }
        linearlayout.addView(card1)

        val card2 = LayoutInflater.from(applicationContext).inflate(R.layout.mycard, ll, false).apply {
            findViewById<TextView>(R.id.cardTitle).text = resources.getString(R.string.https_title)
            findViewById<TextView>(R.id.cardSubTitle).text = resources.getString(R.string.https_url)

            val resultText = findViewById<TextView>(R.id.resultText)

            findViewById<Button>(R.id.button).setOnClickListener {
                getAndSetText(resources.getString(R.string.https_url), resultText)
            }
        }
        linearlayout.addView(card2)
    }

    fun getAndSetText(url: String, textView: TextView) {
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