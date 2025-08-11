package com.example.sqldelightapp.ui.recyclerview
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqldelightapp.R
import persondb.PersonEntity

class PersonDataAdapter: RecyclerView.Adapter<PersonDataViewHolder>() {

    private var personsList = mutableListOf<PersonEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = personsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)
        return PersonDataViewHolder(view)
    }
    override fun onBindViewHolder(holder: PersonDataViewHolder, position: Int) {
        val item = personsList[position]
        val fullName = "${item.firstName} ${item.lastName}"
        holder.personNameView.text = fullName
    }
}