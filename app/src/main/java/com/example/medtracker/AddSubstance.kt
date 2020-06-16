package com.example.medtracker

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.medtracker.CustomViewHolder.Companion.SubstanceImageKey
import com.example.medtracker.CustomViewHolder.Companion.SubstanceLiterKey
import com.example.medtracker.CustomViewHolder.Companion.SubstanceNameKey
import com.example.medtracker.CustomViewHolder.Companion.SubstancePercentageKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_new_substance.*

class AddSubstance : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.add_substance)

            // krijg de title van de adapter met een intent call TODO kan dit cleaner?
            val Name = intent.getStringExtra(CustomViewHolder.SubstanceNameKey)
            val Liter = intent.getStringExtra(CustomViewHolder.SubstanceLiterKey)
            val Percentage = intent.getStringExtra(CustomViewHolder.SubstancePercentageKey)
            val Image = intent.getStringExtra(CustomViewHolder.SubstanceImageKey)

            val name = findViewById<TextView>(R.id.Substance)
            name.text = Name
            val liter = findViewById<TextView>(R.id.liters)
            liter.text = Liter
            val percentage = findViewById<TextView>(R.id.Percentage)
            percentage.text = Percentage
            val image = findViewById<ImageView>(R.id.PreviewImage)
            Picasso.with(this).load(Image).into(image)

            // add button een dialog box geven
            val button = findViewById<Button>(R.id.AddSubstance)
            button.setOnClickListener {
                val builer = AlertDialog.Builder(this)
                builer.setTitle("Substance " + Name + " has been added") // TODO hardcoded
                builer.setMessage("nog meer test") // TODO hardcoded
                builer.setPositiveButton("oke") { dialogInterface: DialogInterface, i: Int -> // TODO hardcoded
                    val intent = Intent(this, MainActivity::class.java)
                    //geef data mee met de button click
                    intent.putExtra("SubstanceName", SubstanceNameKey)
                    intent.putExtra("SubstanceLiter", SubstanceLiterKey)
                    intent.putExtra("SubstancePercentage", SubstancePercentageKey)
                    intent.putExtra("SubstanceImage", SubstanceImageKey)
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
