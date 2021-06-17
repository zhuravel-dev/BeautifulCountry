package com.example.studyprojectrnc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val values: List<String>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null
            init{
                textView = itemView.findViewById(R.id.text_list_item)
            }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CustomViewHolder {

        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_view, viewGroup, false)

        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, position: Int) {

        viewHolder.textView?.text = values[position]

    }

    override fun getItemCount() = values.size

}