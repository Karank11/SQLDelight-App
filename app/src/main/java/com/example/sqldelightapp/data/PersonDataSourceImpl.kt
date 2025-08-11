package com.example.sqldelightapp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.sqldelightapp.PersonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import persondb.PersonEntity
import persondb.PersonEntityQueries

class PersonDataSourceImpl(db: PersonDatabase): PersonDataSource {

    private val queries: PersonEntityQueries = db.personEntityQueries

    override suspend fun getPersonById(id: Long): PersonEntity? {
        return withContext(Dispatchers.IO) {
            queries.getPersonById(id).executeAsOneOrNull()
        }
    }

    override fun getAllPersons(): Flow<List<PersonEntity>> {
        return queries.getAllPersons().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun deletePersonById(id: Long) {
        return withContext(Dispatchers.IO) {
            queries.deletePersonById(id)
        }
    }

    override suspend fun insertPerson(firstName: String, lastName: String, id: Long?) {
        withContext(Dispatchers.IO) {
            queries.insertPerson(id, firstName, lastName)
        }
    }
}