package com.example.studyprojectrnc.presentation.secondScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.data.dataSource.local.model.ModelImageRealm
import com.squareup.picasso.Picasso

class ImageAdapter : ListAdapter<ModelImageRealm, ImageAdapter.ViewHolder>(diffCallback) {

    var onItemClick: ((ModelImageRealm) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        fun bind(modelImageRealm: ModelImageRealm?) {
            Picasso.get().load(modelImageRealm?.largeImageURL).into(ivImage)
            itemView.setOnClickListener {
                modelImageRealm?.let { onItemClick?.invoke(it) }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.title_view_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val modelImageRealm = getItem(position)
        viewHolder.bind(modelImageRealm)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ModelImageRealm>() {
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
                        && oldItem.views == newItem.views
            }
        }
    }
}



