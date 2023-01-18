package com.example.y20230118_yesidhuertas_nycschools.dataaccess.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * [School] This is my School Model
 */
@Entity(tableName = "schools")
data class School(
    @PrimaryKey
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("total_students")
    val totalStudents: String
)