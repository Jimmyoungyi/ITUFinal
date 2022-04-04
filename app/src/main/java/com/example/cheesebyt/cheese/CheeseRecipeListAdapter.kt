package com.example.cheesebyt.cheese

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cheesebyt.R
import com.squareup.picasso.Picasso

class CheeseRecipeListAdapter(private val arrayList: ArrayList<SubSlideItem>) :
    RecyclerView.Adapter<CheeseRecipeListAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.cheeseRecipeImage)
        var title: TextView = view.findViewById(R.id.cheeseRecipeTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cheese_recipes_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = arrayList[position].title
        Picasso.get().load(arrayList[position].image).into(holder.image);
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}