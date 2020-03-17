package com.example.medtracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity

class NewSubstance : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_substance)

//        val btn_new_sub = findViewById(R.id.addnewSubstance2) as Button
//        btn_new_sub.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }

}

