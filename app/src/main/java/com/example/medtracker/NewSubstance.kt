package com.example.medtracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_substance.*
import kotlinx.android.synthetic.main.fragment_substance.*
import okhttp3.*
import java.io.IOException

class NewSubstance : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_substance)

        Substance_recyclerview.layoutManager = LinearLayoutManager(this)
//        Substance_recyclerview.adapter = SubstanceAdapter()

        fetchJson()
    }
    fun fetchJson(){
        val apiUrl = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(apiUrl).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
                runOnUiThread {
                    Substance_recyclerview.adapter = SubstanceAdapter(homeFeed)

                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute")
            }
        })
    }
}

class HomeFeed (val videos: List<Video>)

class Video (val id: Int, val name: String, val link: String, val imageUrl: String, val numberOfViews: Int)

