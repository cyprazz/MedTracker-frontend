package com.example.medtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view.view.*

open class MessageAdapter(val arrayList: ArrayList<Message>, val context: Substance) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button? = itemView.closeBtn //Init the button, later used in onBind
        fun bindItems(message: Message) { //This function is used in onBindViewHolder, it binds the title and message from the array to the cards
            itemView.ctitle.text = message.title
            itemView.cmessage.text = message.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //This creates the view
        val v = LayoutInflater.from(parent.context) .inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])  //calls bindItems to cycle through the array to create the cards.
        holder.button?.closeBtn?.setOnClickListener {// This creates the onclick listener for the remove button, and removes it from the list, and the view.
            arrayList.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int { //This counts the items (auto generated)
        return arrayList.size
    }

}
