package com.example.medtracker

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_new_substance.*

class AddSubstance : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.new_subs_add_substance)

            // krijgt de title van de adapter met een intent call
            val Name = intent.getStringExtra(CustomViewHolder.Substance_Name_key)
            val Liter = intent.getStringExtra(CustomViewHolder.Substance_Liter_key)
            val Percentage = intent.getStringExtra(CustomViewHolder.Substance_Percentage_key)
            val Image = intent.getStringExtra(CustomViewHolder.Substance_Image_key)

            val name = findViewById<TextView>(R.id.Substance)
            name.text = Name
            val liter = findViewById<TextView>(R.id.liters)
            liter.text = Liter
            val percentage = findViewById<TextView>(R.id.Percentage)
            percentage.text = Percentage
            val image = findViewById<ImageView>(R.id.Preview_image)
            Picasso.with(this).load(Image).into(image)

            // add button een dialog box geven
            val button = findViewById<Button>(R.id.AddSubstance)
            button.setOnClickListener {
                val builer = AlertDialog.Builder(this)
                builer.setTitle("test")
                builer.setMessage("nog meer test")
                builer.setPositiveButton("oke",{ dialogInterface: DialogInterface, i: Int ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                })
                builer.show()
            }

            floating_action_button.setOnClickListener{
                val intent = Intent(this, NewSubstance::class.java)
                startActivity(intent)
            }
        }

}
