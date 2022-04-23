package com.example.cheesebyt.forYou

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cheesebyt.R
import com.squareup.picasso.Picasso

class ForYouListAdapter (
    private val context: Activity,
    private val arrayList: ArrayList<ForYouListItem>
) : ArrayAdapter<ForYouListItem>(
    context,
    R.layout.for_you_list_item,
    arrayList
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.for_you_list_item, null)

        val postImage: ImageView = view.findViewById(R.id.postImage)
        val postName: TextView = view.findViewById(R.id.postName)
        val postDescription: TextView = view.findViewById(R.id.postDescription)

        Picasso.get().load(arrayList[position].postImage).into(postImage);
        postName.text = arrayList[position].postName
        postDescription.text = arrayList[position].postDescription

        return view
    }
}