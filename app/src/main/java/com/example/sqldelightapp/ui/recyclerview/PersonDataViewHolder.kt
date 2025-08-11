package com.example.sqldelightapp.ui.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sqldelightapp.R

class PersonDataViewHolder(itemView: View) : ViewHolder(itemView) {
    val personNameView: TextView = itemView.findViewById(R.id.person_name)
    val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)
}