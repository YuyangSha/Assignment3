package com.example.traveltracker

import com.example.traveltracker.Attraction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltracker.R

class AttractionAdapter(
    private val attractions: List<Attraction>,
    private val itemClick: (Attraction) -> Unit
) : RecyclerView.Adapter<AttractionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.attraction_name)
        val description: TextView = view.findViewById(R.id.attraction_description)
        val image: ImageView = view.findViewById(R.id.attraction_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attraction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val attraction = attractions[position]
        holder.name.text = attraction.name
        holder.description.text = attraction.description
        holder.image.setImageResource(attraction.imageResId)

        holder.itemView.setOnClickListener { itemClick(attraction) }
    }

    override fun getItemCount() = attractions.size
}
