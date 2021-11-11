package com.example.studyapproom.Data

import androidx.lifecycle.LiveData

class DetailsRepository(private val detailsDao: DetailsDao ) {

    // Kotlin Data
    val getKotlinDetails: LiveData<List<KotlinDetails>> = detailsDao.getKotlinDetails()

    suspend fun addKotlinDetails(kotlinDetails : KotlinDetails){
        detailsDao.addKotlinDetails(kotlinDetails)
    }

    suspend fun updateKotlinDetails(kotlinDetails : KotlinDetails){
        detailsDao.updateKotlinDetails(kotlinDetails)
    }

    suspend fun deleteKotlinDetails(kotlinDetails : KotlinDetails){
        detailsDao.deleteKotlinDetails(kotlinDetails)
    }

    //----------------------------------------------------------------------------------------------
    // Android Data
    val getAndroidDetails: LiveData<List<AndroidDetails>> = detailsDao.getAndroidDetails()

    suspend fun addAndroidDetails(androidDetails: AndroidDetails){
        detailsDao.addAndroidDetails(androidDetails)
    }

    suspend fun updateAndroidDetails(androidDetails: AndroidDetails){
        detailsDao.updateAndroidDetails(androidDetails)
    }
    suspend fun deleteAndroidDetails(androidDetails: AndroidDetails){
        detailsDao.deleteAndroidDetails(androidDetails)
    }

}