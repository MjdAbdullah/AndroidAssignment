package com.example.studyapproom.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DetailsDao {
    @Query("SELECT * FROM KotlinDetails ORDER BY ID ASC")
    fun getKotlinDetails(): LiveData<List<KotlinDetails>>

    @Insert
    fun addKotlinDetails(kotlinDetail: KotlinDetails)

    @Delete
    fun deleteKotlinDetails(kotlinDetail: KotlinDetails)

    @Update
    fun updateKotlinDetails(kotlinDetail: KotlinDetails)

    @Query("SELECT * FROM AndroidDetails ORDER BY ID ASC")
    fun getAndroidDetails(): LiveData<List<AndroidDetails>>

    @Insert
    fun addAndroidDetails(androidDetail: AndroidDetails)

    @Delete
    fun deleteAndroidDetails(androidDetail: AndroidDetails)

    @Update
    fun updateAndroidDetails(androidDetail: AndroidDetails)
}