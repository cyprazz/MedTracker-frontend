package com.example.medtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(Substance())

        toolbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_calender-> {
                    title=resources.getString(R.string.calender)
                    loadFragment(Calender())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_substances-> {
                    title=resources.getString(R.string.substances)
                    loadFragment(Substance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_settings-> {
                    title=resources.getString(R.string.settings)
                    loadFragment(Settings())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
