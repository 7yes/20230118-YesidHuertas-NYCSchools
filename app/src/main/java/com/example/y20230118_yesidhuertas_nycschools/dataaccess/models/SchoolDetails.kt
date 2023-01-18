package com.example.y20230118_yesidhuertas_nycschools.dataaccess.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *  [SchoolDetails] this is my School Detail Model with the extra info for the details fragment
 */
@Entity(tableName = "school_details")
data class SchoolDetails(
    @PrimaryKey
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String,
    @SerializedName("school_name")
    val schoolName: String
)