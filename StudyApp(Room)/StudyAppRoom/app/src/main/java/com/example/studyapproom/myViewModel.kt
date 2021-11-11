package com.example.studyapproom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.studyapproom.Data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class myViewModel(application: Application): AndroidViewModel(application)  {

    private val repository: DetailsRepository
    private val androidDetails: LiveData<List<AndroidDetails>>
    private val kotlinDetails: LiveData<List<KotlinDetails>>

    init {
        val detailsDao = DetailsDatabase.getinstance(application).DetailsDao()
        repository = DetailsRepository(detailsDao)
        androidDetails = repository.getAndroidDetails
        kotlinDetails = repository.getKotlinDetails
    }
    //---------------------------------------------------------------------------------------------
    // Android Data
    fun getAndroidDetails(): LiveData<List<AndroidDetails>>{
        return androidDetails
    }

    fun addAndroidDetails(newDetails: AndroidDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addAndroidDetails(newDetails)
        }
    }

    fun updateAndroidDetails(updateDetails: AndroidDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateAndroidDetails(updateDetails)
        }
    }

    fun deleteAndroidDetails(deleteDetails: AndroidDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAndroidDetails(deleteDetails)
        }
    }

    //---------------------------------------------------------------------------------------------
    // Kotlin Data
    fun getKotlinDetails():LiveData<List<KotlinDetails>> {
        return kotlinDetails
    }

    fun addKotlinDetails(newDetails: KotlinDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addKotlinDetails(newDetails)
        }
    }

    fun updateKotlinDetails(updateDetails: KotlinDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateKotlinDetails(updateDetails)
        }
    }

    fun deleteKotlinDetails(deleteDetails: KotlinDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteKotlinDetails(deleteDetails)
        }
    }

}