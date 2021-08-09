/*
package com.example.studyprojectrnc.ui.adapters

import android.content.DialogInterface
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyprojectrnc.R


// spinner
class DialogAdapter :
    RecyclerView.Adapter<DialogAdapter.CustomViewHolder>() {

    private lateinit var listOfSize : List<Size>
    var onItemClick: ((Size) -> Unit)? = null

    fun setData (response: List<Size>) {
        listOfSize = listOf()
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(size: Size, clickLictener: DialogInterface.OnClickListener) {
            val dialogTitle: TextView = itemView.findViewById(R.id.dialogChangeResolution)

            itemView.setOnClickListener {
                clickLictener.onClick()
            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Log.e("Adapter", "bind holder with position $position")
        (holder as? CustomViewHolder)?.bind(listOfSize[position], onItemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dialog, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
*/
