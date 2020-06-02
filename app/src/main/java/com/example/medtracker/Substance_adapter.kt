package com.example.medtracker

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.substance_row.view.*

class SubstanceAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val row = layoutInflater.inflate(R.layout.substance_row, parent, false)
        return CustomViewHolder(row)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos.get(position)
        holder.view.Substance_name.text = video.name
        holder.view.Liters.text = video.id.toString()
        holder.view.Percantage.text = video.numberOfViews.toString()
        val thumbnailimageview = holder.view.Preview_image
        Picasso.with(holder.view.context).load(video.imageUrl).into(thumbnailimageview)

        // maakt de video publicly accesable
        holder?.video = video

    }

}

// video is de functie waar de json data binnenkomt
class CustomViewHolder(val view: View, var video: Video?= null): RecyclerView.ViewHolder(view){
    //hier stuur je de info via een intent mee naar de add_substance
    companion object{
        const val SubstanceNameKey = "Substance title"
        const val SubstanceLiterKey = "Substance liter"
        const val SubstancePercentageKey = "Substance Percentage"
        const val SubstanceImageKey = "Substance Image"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, AddSubstance::class.java)

            //meesturen van gegevens naar de addsubstance pagina
            intent.putExtra(SubstanceNameKey, video?.name)
            intent.putExtra(SubstanceLiterKey, video?.id.toString())
            intent.putExtra(SubstancePercentageKey, video?.numberOfViews.toString())
            intent.putExtra(SubstanceImageKey, video?.imageUrl)

            view.context.startActivity(intent)
        }
    }
}
