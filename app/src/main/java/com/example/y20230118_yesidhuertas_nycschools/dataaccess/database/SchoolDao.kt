package com.example.y20230118_yesidhuertas_nycschools.dataaccess.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.School
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.SchoolDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Query("SELECT * FROM schools")
    fun getSchools(): Flow<List<School>>

    @Insert(entity = School::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSchools(schools: List<School>)
}

@Dao
interface SchoolDetailsDao {

    @Query("SELECT * FROM school_details WHERE dbn = :dbn")
    fun getDetailsByDbn(dbn: String): Flow<SchoolDetails>

    @Insert(entity = SchoolDetails::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDetails(details: List<SchoolDetails>)
}