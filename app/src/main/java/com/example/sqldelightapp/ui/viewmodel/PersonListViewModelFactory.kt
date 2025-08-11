package com.example.sqldelightapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sqldelightapp.data.PersonDataSource

class PersonListViewModelFactory(private val personDataSource: PersonDataSource): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonListViewModel::class.java)) {
            return PersonListViewModel(personDataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}