package com.example.medtracker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_substance.*
import com.example.medtracker.MessageAdapter as Message_adapter


class Substance : Fragment() {
    private val arrayList = ArrayList<com.example.medtracker.Message>()
//    val deleteButton = close_btn
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_substance, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        empty.toggleVisibility() //use to toggle the empty message
        createList()
        buildRecyclerView()


        addnewSubstance2.setOnClickListener{
            val intent = Intent(activity, NewSubstance::class.java)
            startActivity(intent)
        } //This is the button to add a new substance
    }

    fun buildRecyclerView(){
        val messageAdapter = Message_adapter(arrayList, this)
        card_recycler.layoutManager = LinearLayoutManager(context)
        card_recycler.adapter = messageAdapter
    }//This builds the recycler

    fun View.toggleVisibility() {
        if (visibility == View.VISIBLE) {
            visibility = View.INVISIBLE
        } else {
            visibility = View.VISIBLE
        }
    } //This is an function to toggle visibility of the empty message

    fun createList() {
        arrayList.add(com.example.medtracker.Message("Nieuwtjes1", "Nog zeer weinig1."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes2", "Nog zeer weinig2."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes3", "Nog zeer weinig3."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes4", "Nog zeer weinig4."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes5", "Nog zeer weinig5."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes6", "Nog zeer weinig6."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes7", "Nog zeer weinig7."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes8", "Nog zeer weinig8."))
        arrayList.add(com.example.medtracker.Message("Nieuwtjes9", "Nog zeer weinig9."))
    }//This creates a list TODO: make this an API call\
}
