package com.example.medtracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chintanpatel.materialeventcalendar.EventItem
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.json.responseJson
import kotlinx.android.synthetic.main.calender_month.*
import kotlinx.android.synthetic.main.fragment_calender.*


class CalenderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_calender, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragmentAdapter = fragmentManager?.let { CalenderAdapter(it) }
        viewPager.adapter = fragmentAdapter

        val calendarView = viewPager

        getAgenda()
        tabLayout.setupWithViewPager(viewPager)

    }
//
//    class MyCalenderView : CalendarView(null,null,null,null ) {
//    }

    fun getAgenda() {
        //setting up the request
        FuelManager.instance.removeAllRequestInterceptors()
        Fuel.get("http://192.168.1.2:8080/agendaentries/1") //TODO make this request to server
            .responseJson { request, response, result ->
                result.fold(success = { json ->
                    var i = 0
                    val agendaArray = json.array()
                    while(agendaArray.length() > i){
                        println(agendaArray.getJSONObject(i).get("id"))
                        i++
                    }
                }, failure = { error ->
                    Log.e("Failure", error.toString())
                })

            }


    }



}





