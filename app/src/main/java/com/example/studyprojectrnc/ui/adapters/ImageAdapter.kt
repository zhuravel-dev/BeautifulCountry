package com.example.studyprojectrnc.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ImageAdapter :
    PagingDataAdapter<ModelImageRealm, ImageAdapter.ViewHolder>(ImageDiffUtilCallBack()) {

    var onItemClick: ((ModelImageRealm) -> Unit)? = null
    private val itemList = mutableListOf<ModelImageRealm>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        fun bind(modelImageRealm: ModelImageRealm) {
            Picasso.get().load(modelImageRealm.largeImageURL).into(ivImage, object : Callback {
                override fun onSuccess() {
                    Log.i("TAG", "onSuccess")
                }
                override fun onError(e: Exception?) {
                    Log.i("TAG", "onError")
                }
            })
            itemView.setOnClickListener {
                onItemClick?.invoke(modelImageRealm)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.title_view_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val modelImageRealm = itemList[position]
        viewHolder.bind(modelImageRealm)
    }

    override fun getItemCount() = itemList.size

    fun updateTitleData(data: List<ModelImageRealm>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(this.itemList, data))
        itemList.clear()
        itemList.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
    class ImageDiffUtilCallBack : DiffUtil.ItemCallback<ModelImageRealm>() {
        override fun areItemsTheSame(
            oldItem: ModelImageRealm,
            newItem: ModelImageRealm
        ): Boolean {
            return oldItem.largeImageURL == newItem.largeImageURL
        }

        override fun areContentsTheSame(
            oldItem: ModelImageRealm,
            newItem: ModelImageRealm
        ): Boolean {
            return oldItem.largeImageURL == newItem.largeImageURL
                    && oldItem.num == newItem.num
        }

    }
}
private class DiffUtilCallback(
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


