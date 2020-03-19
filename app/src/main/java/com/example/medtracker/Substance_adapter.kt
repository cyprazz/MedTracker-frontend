package com.example.medtracker

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.substance_row.view.*

class SubstanceAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>(){
    val titles = listOf("bier","ander bier","weer ander bier","geen bier")

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val row = layoutInflater.inflate(R.layout.substance_row, parent, false)
        return CustomViewHolder(row)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//       val titel = titles.get(position)
        val video = homeFeed.videos.get(position)
        holder.view.Substance_name.text = video.name
        holder.view.Liters.text = video.id.toString()
        holder.view.Percantage.text = video.numberOfViews.toString()
        val thumbnailimageview = holder.view.Preview_image
        Picasso.with(holder.view.context).load(video.imageUrl).into(thumbnailimageview)
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){
    init {
        view.setOnClickListener {
            val intent = Intent(view.context, AddSubstance::class.java)
            view.context.startActivity(intent)
        }
    }
}
