package com.example.medtracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_substance.*

class AddSubstance : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.new_subs_add_substance)

            floating_action_button.setOnClickListener{
                val intent = Intent(this, NewSubstance::class.java)
                startActivity(intent)
            }
        }
}
