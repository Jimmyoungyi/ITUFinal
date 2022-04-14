package com.example.cheesebyt.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.cheesebyt.R
import com.squareup.picasso.Picasso

class CheeseTypeListAdapter (
    private val context: FragmentActivity,
    private val arrayList: ArrayList<CheeseTypeListItem>
) : ArrayAdapter<CheeseTypeListItem>(
    context,
    R.layout.cheese_type_list_item,
    arrayList
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.cheese_type_list_item, null)

        val imageView: ImageView = view.findViewById(R.id.cheeseTypeListItemImage)
        val textView: TextView = view.findViewById(R.id.cheeseTypeListItemText)


        Picasso.get().load(arrayList[position].imgURL).into(imageView)
        textView.text = arrayList[position].name


        return view
    }
}