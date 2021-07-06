package com.example.studyprojectrnc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.data.db.ModelImageRealm
import com.squareup.picasso.Picasso

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val itemList = mutableListOf<ModelImageRealm>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.title_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(itemList[position].largeImageURL).into(viewHolder.ivImage)
    }

    override fun getItemCount() = itemList.size

    fun addData(images: List<ModelImageRealm>?) {
        itemList.apply {
            clear()
            addAll(images ?: arrayListOf())
        }
        notifyDataSetChanged()
    }

    fun updateTitleData(data: List<ModelImageRealm>) {
        val diffResult = DiffUtil.calculateDiff(ImageDiffUtilCallback(this.itemList, data))
        itemList.clear()
        itemList.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}

private class ImageDiffUtilCallback(
    val newPersons: List<ModelImageRealm>,
    val oldPersons: List<ModelImageRealm>,
) : DiffUtil.Callback() {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition] == newPersons[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].num == newPersons[newItemPosition].num
    }

    override fun getNewListSize(): Int {
        return newPersons.size
    }

    override fun getOldListSize(): Int {
        return oldPersons.size
    }
}


