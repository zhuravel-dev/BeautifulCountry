package com.example.studyprojectrnc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.repository.model.Hits
import com.squareup.picasso.Picasso

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val itemList = mutableListOf<Hits>()
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.title_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Picasso.get().load(itemList[position].largeImageURL).into(viewHolder.ivImage)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = itemList.size

    fun addData(images: List<Hits>?) {
        itemList.apply {
            clear()
            addAll(images ?: arrayListOf())
        }
        notifyDataSetChanged()
    }
}
//
//    private val itemList = mutableListOf<Hits>()
//
//    override fun getItemCount(): Int = itemList.size
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        holder.bind(itemList[position])
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.title_view_item, parent, false)
//        return ImageViewHolder(view)
//    }
//
//    fun addData(images: List<Hits>?) {
//        itemList.apply {
//            clear()
//            addAll(images ?: arrayListOf())
//        }
//        notifyDataSetChanged()
//    }
//
//    fun updateTitleData(data: List<Hits>) {
//        val diffResult = DiffUtil.calculateDiff(TitleDiffUtilCallback(this.itemList, data))
//        itemList.clear()
//        itemList.addAll(data)
//        diffResult.dispatchUpdatesTo(this)
//    }
//
//    inner class ImageViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
////        private val binding = TitleViewItemBinding.bind(item)
//        fun bind(data: Hits) {
//            val image: ImageView = item.findViewById(R.id.ivImage)
//            Picasso.get().load(data.largeImageURL).into(image)
//            Log.d("TAG", "image:$image, data:${data.largeImageURL}")
//        }
//    }
//
//}

private class TitleDiffUtilCallback(
    val newPersons: List<Hits>,
    val oldPersons: List<Hits>,
) : DiffUtil.Callback() {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition] == newPersons[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].id == newPersons[newItemPosition].id
    }

    override fun getNewListSize(): Int {
        return newPersons.size
    }

    override fun getOldListSize(): Int {
        return oldPersons.size
    }

}


