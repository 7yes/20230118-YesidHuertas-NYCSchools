package com.example.y20230118_yesidhuertas_nycschools.di

import android.content.Context
import androidx.room.Room
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.database.DB_NAME
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.database.DatabaseRepository
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.database.IDatabaseRepository
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.database.SchoolsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesDatabaseRepository(database: SchoolsDB): IDatabaseRepository =
        DatabaseRepository(database)

    @Provides
    fun providesSchoolDB(@ApplicationContext applicationContext: Context): SchoolsDB =
        Room.databaseBuilder(
            applicationContext,
            SchoolsDB::class.java,
            DB_NAME
        ).build()
}