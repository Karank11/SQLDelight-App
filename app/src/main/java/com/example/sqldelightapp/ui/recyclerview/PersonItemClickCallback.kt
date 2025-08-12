package com.example.sqldelightapp.ui.recyclerview

import persondb.PersonEntity

interface PersonItemClickCallback {
    fun onPersonItemClick(person: PersonEntity)
    fun onDeleteButtonClick(id: Long)
}