package com.example.studyprojectrnc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.databinding.TitleViewItemBinding
import com.example.studyprojectrnc.repository.model.Hits
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.title_view_item.view.*
import java.util.Collections.addAll

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private val itemList = ArrayList<Hits>()

    class ImageViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = TitleViewItemBinding.bind(item)
        val image: ImageView = itemView.ivImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.title_view_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get().load(itemList[position].webformatURL).into(holder.image)

    }

    override fun getItemCount(): Int = itemList.size

    fun addData(images:List<Hits>?) {
        itemList.apply {
            clear()
            addAll(images ?: arrayListOf())
        }
        notifyDataSetChanged()
    }

   fun updateTitleData(data: List<Hits>) {
        val diffResult = DiffUtil.calculateDiff(TitleDiffUtilCallback(this.itemList, data))
        itemList.clear()
        itemList.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}

   private class TitleDiffUtilCallback(
        val newPersons: List<Hits>,
        val oldPersons: List<Hits>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldPersons.size
    }

    override fun getNewListSize(): Int {
        return newPersons.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].id == newPersons[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition] == newPersons[newItemPosition]
    }
}


