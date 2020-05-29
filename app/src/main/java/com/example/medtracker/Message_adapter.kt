package com.example.medtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view.view.*

class Message_adapter(val arrayList: ArrayList<Message>, val context: Substance) :
    RecyclerView.Adapter<Message_adapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(message: Message) {
            itemView.ctitle.text = message.title
            itemView.cmessage.text = message.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context) .inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
        holder.itemView.setTag(position)
        var currentItem = arrayList.get(position)

//        fun removeItem(position: Int){
//            arrayList.remove(position)
//        }


    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}