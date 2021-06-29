package com.example.studyprojectrnc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.databinding.TitleViewItemBinding

class TitleAdapter : RecyclerView.Adapter<TitleAdapter.CustomViewHolder>() {
    private val itemList = ArrayList<TitleData>()

    class CustomViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = TitleViewItemBinding.bind(item)
        fun bind(titleData: TitleData) = with(binding) {
            tvTitle.text = titleData.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.title_view_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

   /* fun updateTitleData(data: List<TitleData>) {
        val diffResult = DiffUtil.calculateDiff(TitleDiffUtilCallback(this.itemList, data))
        itemList.clear()
        itemList.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
    */
}

   /* private class TitleDiffUtilCallback(
        val newPersons: List<TitleData>,
        val oldPersons: List<TitleData>
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
*/

