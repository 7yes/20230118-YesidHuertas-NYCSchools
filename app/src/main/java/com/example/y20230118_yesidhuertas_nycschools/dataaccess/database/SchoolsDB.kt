package com.example.y20230118_yesidhuertas_nycschools.dataaccess.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.School
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.SchoolDetails

@Database(
    entities = [
        School::class,
        SchoolDetails::class
    ],
    version = DB_VERSION
)
abstract class SchoolsDB : RoomDatabase() {

    abstract fun schoolDao(): SchoolDao
    abstract fun schoolDetailsDao(): SchoolDetailsDao
}

private const val DB_VERSION = 1
const val DB_NAME = "schools_db"