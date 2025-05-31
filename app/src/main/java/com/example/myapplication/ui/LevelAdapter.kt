package com.example.myapplication.ui

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.WorkoutLevel

class LevelAdapter(
    private val items: List<WorkoutLevel>
) : RecyclerView.Adapter<LevelAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgLvl)
        fun bind(item: WorkoutLevel) {
            img.setImageResource(item.imageResId!!)
        }
  /*      fun bind(item: WorkoutLevel) {
            img.visibility = View.VISIBLE
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lvl, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
