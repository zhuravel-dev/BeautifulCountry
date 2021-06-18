package com.example.studyprojectrnc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.databinding.ItemViewBinding

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    val itemList = ArrayList<List>()

    class CustomViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemViewBinding.bind(item)
        fun bind(list: List) = with(binding) {
            tvTitle.text = list.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun addText(list: List) {
        itemList.add(list)
        notifyDataSetChanged()
    }
}