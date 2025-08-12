package com.example.sqldelightapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sqldelightapp.ui.recyclerview.PersonDataAdapter
import com.example.sqldelightapp.ui.recyclerview.PersonItemClickCallback
import com.example.sqldelightapp.ui.viewmodel.PersonListViewModel
import com.example.sqldelightapp.ui.viewmodel.PersonListViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import persondb.PersonEntity

class MainActivity : AppCompatActivity() {
    private lateinit var enterButton: Button
    private lateinit var firstEditText: EditText
    private lateinit var lastEditText: EditText
    private lateinit var listView: RecyclerView
    private val personDataSource = PersonApplication.getPersonDataSource()
    private val viewModelFactory = PersonListViewModelFactory(personDataSource)
    private val viewModel:PersonListViewModel by viewModels { viewModelFactory }

    private val itemClickCallback = object : PersonItemClickCallback {
        override fun onPersonItemClick(person: PersonEntity) {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("View Person")
                .setMessage("Name: ${person.firstName} ${person.lastName}")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        override fun onDeleteButtonClick(id: Long) {
            viewModel.onDeletePersonClick(id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enterButton = findViewById(R.id.enter_button)
        firstEditText = findViewById(R.id.first_name_input)
        lastEditText = findViewById(R.id.last_name_input)
        listView = findViewById(R.id.recyclerView)

        val adapter = PersonDataAdapter(itemClickCallback)
        listView.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.personList.collect {
                adapter.submitList(it)
            }
        }

        enterButton.setOnClickListener {
            val firstName = firstEditText.text.toString()
            val lastName = lastEditText.text.toString()
            viewModel.onFirstNameChange(firstName)
            viewModel.onLastNameChange(lastName)
            viewModel.onInsertPersonClick()

            firstEditText.text.clear()
            lastEditText.text.clear()
        }
    }
}