package com.example.medtracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_calender.*
import kotlinx.android.synthetic.main.substance_search.*

class Calender : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_calender, container, false)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ButtonDay.setOnClickListener{
            ButtonDay.setBackgroundResource(R.drawable.button_background_selected)
            ButtonWeek.setBackgroundResource(R.drawable.button_background)
            ButtonMonth.setBackgroundResource(R.drawable.button_background)
        }
        ButtonWeek.setOnClickListener{
            ButtonDay.setBackgroundResource(R.drawable.button_background)
            ButtonWeek.setBackgroundResource(R.drawable.button_background_selected)
            ButtonMonth.setBackgroundResource(R.drawable.button_background)
            
        }

        ButtonMonth.setOnClickListener{
            ButtonDay.setBackgroundResource(R.drawable.button_background)
            ButtonWeek.setBackgroundResource(R.drawable.button_background)
            ButtonMonth.setBackgroundResource(R.drawable.button_background_selected)
        }

    }


}
