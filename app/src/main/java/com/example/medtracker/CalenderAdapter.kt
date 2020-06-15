package com.example.medtracker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter

import androidx.fragment.app.FragmentManager

class CalenderAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Tab1Day()
            }
            1 -> Tab2Week()
            else -> {
                return Tab3Month()
            }
        }
    }

    fun getAgendaEntries(){

    }


    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Dag"
            1 -> "Week"
            else -> {
                return "Maand"
            }
        }
    }
}