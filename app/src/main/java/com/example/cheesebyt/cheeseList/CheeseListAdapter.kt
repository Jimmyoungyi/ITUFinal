package com.example.cheesebyt.cheeseList

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.cheesebyt.R
import com.squareup.picasso.Picasso

class CheeseListAdapter (
    private val context: Activity,
    private val arrayList: ArrayList<CheeseListItem>
) : ArrayAdapter<CheeseListItem>(
    context,
    R.layout.cheese_list_item,
    arrayList
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.cheese_list_item, null)

        val cheeseName: TextView = view.findViewById(R.id.cheeseName)
        val cheeseBkImage: ImageView = view.findViewById(R.id.cheeseBkImage)
        val cheeseIconImage: ImageView = view.findViewById(R.id.cheeseIconImage)
        val cheeseRate: RatingBar = view.findViewById(R.id.cheeseRate)
        val cheesePrice: TextView = view.findViewById(R.id.cheesePrice)
        val cheeseDescription: TextView = view.findViewById(R.id.cheeseDescription)

        cheeseName.text = arrayList[position].cheeseName
        Picasso.get().load(arrayList[position].cheeseBkImage).into(cheeseBkImage);
        Picasso.get().load(arrayList[position].cheeseIconImage).into(cheeseIconImage);
        cheeseRate.rating = arrayList[position].cheeseRate
        cheesePrice.text = "$" + arrayList[position].cheesePrice.toString()
        cheeseDescription.text = arrayList[position].cheeseDescription

        return view
    }
}