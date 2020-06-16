package com.example.medtracker


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_new_substance.*
import okhttp3.*
import java.io.IOException

class NewSubstance : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_substance)

        val sharedPreferences = getSharedPreferences("Token", 0)
        val apiToken = sharedPreferences.getString("Token", null)

        Substance_recyclerview.layoutManager = LinearLayoutManager(this)

        floatingActionButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        }

        fetchJson(apiToken)
    }
    fun fetchJson(t : String?){
        val apiUrl = "http://192.168.2.19:8080/creators/1/drugs"
        val request = Request.Builder().url(apiUrl).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val drugFeed = gson.fromJson(body, DrugFeed::class.java)
                runOnUiThread {
                    Substance_recyclerview.adapter = SubstanceAdapter(drugFeed)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute")
            }
        })
    }
}


