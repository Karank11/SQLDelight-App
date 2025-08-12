package com.example.sqldelightapp

import android.app.Application
import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.sqldelightapp.data.PersonDataSource
import com.example.sqldelightapp.data.PersonDataSourceImpl

class PersonApplication: Application() {

    companion object {
        private lateinit var appContext: Context
        private var personDataSource: PersonDataSource? = null

        fun getPersonDataSource(): PersonDataSource {
            synchronized(this) {
                var instance = personDataSource
                if (instance == null) {
                    val driver = AndroidSqliteDriver(PersonDatabase.Schema, appContext, "person.db")
                    val database = PersonDatabase(driver)
                    instance = PersonDataSourceImpl(database)
                    personDataSource = instance
                }
                return instance
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}