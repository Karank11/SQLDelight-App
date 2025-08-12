package com.example.sqldelightapp.ui.recyclerview
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.sqldelightapp.R
import persondb.PersonEntity

class PersonDataAdapter(private val itemClickCallback: PersonItemClickCallback): ListAdapter<PersonEntity, PersonDataViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)
        return PersonDataViewHolder(view)
    }
    override fun onBindViewHolder(holder: PersonDataViewHolder, position: Int) {
        val item = getItem(position)
        val fullName = "${item.firstName} ${item.lastName}"
        holder.personNameView.text = fullName

        holder.personNameView.setOnClickListener {
            itemClickCallback.onPersonItemClick(item)
        }

        holder.deleteButton.setOnClickListener {
            itemClickCallback.onDeleteButtonClick(item.id)
        }
    }

    class PersonDiffCallback: DiffUtil.ItemCallback<PersonEntity>() {
        override fun areItemsTheSame(oldItem: PersonEntity, newItem: PersonEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PersonEntity, newItem: PersonEntity): Boolean {
            return oldItem == newItem
        }

    }
}