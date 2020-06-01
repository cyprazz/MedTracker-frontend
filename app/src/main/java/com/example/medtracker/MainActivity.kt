package com.example.medtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(Substance())
        toolbar.checkItem(R.id.navSubstances)

        toolbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navCalender-> {
                    title=resources.getString(R.string.calender)
                    loadFragment(Calender())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navSubstances-> {
                    title=resources.getString(R.string.substances)
                    loadFragment(Substance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navSettings-> {
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
    private fun BottomNavigationView.checkItem(actionId: Int) {
        menu.findItem(actionId)?.isChecked = true
    }
}
