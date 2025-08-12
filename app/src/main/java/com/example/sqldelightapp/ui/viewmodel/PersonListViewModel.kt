package com.example.sqldelightapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sqldelightapp.data.PersonDataSource
import kotlinx.coroutines.launch

class PersonListViewModel(private val dataSource: PersonDataSource): ViewModel() {

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> get() = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> get() = _lastName

    val personList = dataSource.getAllPersons()

    fun onFirstNameChange(inputText: String) {
        _firstName.value = inputText
    }

    fun onLastNameChange(inputText: String) {
        _lastName.value = inputText
    }

    fun onInsertPersonClick() {
        if (firstName.value.isNullOrEmpty() || lastName.value.isNullOrEmpty()) {
            return
        }

        viewModelScope.launch {
            dataSource.insertPerson(firstName.value.toString(), lastName.value.toString())
        }
        _firstName.value = ""
        _lastName.value = ""
    }

    fun onDeletePersonClick(id: Long) {
        viewModelScope.launch {
            dataSource.deletePersonById(id)
        }
    }

}