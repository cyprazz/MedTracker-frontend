package com.example.medtracker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_substance.*
import com.example.medtracker.MessageAdapter as messageAdapter


class Substance : Fragment() {
    private val arrayList = ArrayList<com.example.medtracker.Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_substance, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        empty.toggleVisibility() //use to toggle the empty message
        createList()
        buildRecyclerView()


        newSubstanceButton.setOnClickListener {
            val intent = Intent(activity, NewSubstance::class.java)
            startActivity(intent)
        } //This is the button to add a new substance
    }

    fun buildRecyclerView() {
        val messageAdapter = messageAdapter(arrayList, this)
        cardRecycler.layoutManager = LinearLayoutManager(context)
        cardRecycler.adapter = messageAdapter
    }//This builds the recycler

    fun View.toggleVisibility() {
        if (visibility == View.VISIBLE) {
            visibility = View.INVISIBLE
        } else {
            visibility = View.VISIBLE
        }
    } //This is an function to toggle visibility of the empty message

    fun createList() {//This creates a list
        arrayList.add(com.example.medtracker.Message("Nieuwtjes1", "Nog zeer weinig1."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes2", "Nog zeer weinig2."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes3", "Nog zeer weinig3."))
    }

}
