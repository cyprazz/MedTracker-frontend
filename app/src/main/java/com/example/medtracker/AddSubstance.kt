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
            setContentView(R.layout.add_substance)

            // krijg de title van de adapter met een intent call TODO kan dit cleaner?
            val Name = intent.getStringExtra(CustomViewHolder.Substance_Name_Key)
            val Liter = intent.getStringExtra(CustomViewHolder.Substance_Liter_Key)
            val Percentage = intent.getStringExtra(CustomViewHolder.Substance_Percentage_Key)
            val Image = intent.getStringExtra(CustomViewHolder.Substance_Image_Key)

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
                builer.setTitle("test") // TODO hardcoded
                builer.setMessage("nog meer test") // TODO hardcoded
                builer.setPositiveButton("oke") { dialogInterface: DialogInterface, i: Int -> // TODO hardcoded
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                builer.show()
            }

            floatingActionButton.setOnClickListener{
                val intent = Intent(this, NewSubstance::class.java)
                startActivity(intent)
            }
        }

}
