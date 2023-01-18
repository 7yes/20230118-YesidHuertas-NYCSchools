package com.example.y20230118_yesidhuertas_nycschools.dataaccess.database

import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.School
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.SchoolDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IDatabaseRepository {

    fun getSchools(): Flow<List<School>>
    fun getDetailsByDbn(dbn: String): Flow<SchoolDetails?>
    suspend fun saveSchools(schools: List<School>)
    suspend fun saveDetails(details: List<SchoolDetails>)
}

class DatabaseRepository @Inject constructor(
    private val database: SchoolsDB
) : IDatabaseRepository {

    override fun getSchools(): Flow<List<School>> = database.schoolDao().getSchools()

    override fun getDetailsByDbn(dbn: String): Flow<SchoolDetails?> =
        database.schoolDetailsDao().getDetailsByDbn(dbn)


    override suspend fun saveSchools(schools: List<School>) =
        database.schoolDao().saveSchools(schools)

    override suspend fun saveDetails(details: List<SchoolDetails>) =
        database.schoolDetailsDao().saveDetails(details)
}