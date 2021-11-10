package com.example.headsup_room.Data

import androidx.lifecycle.LiveData

class CelebrityRepository(private val celebrityDao: CelebrityDao ) {

    val getCelebrity: LiveData<List<Celebrity>> = celebrityDao.getCelebrity()

    suspend fun addCelebrity(celebrity : Celebrity){
        celebrityDao.addCelebrity(celebrity)
    }

    suspend fun updateCelebrity(celebrity : Celebrity){
        celebrityDao.updateCelebrity(celebrity)
    }

    suspend fun deleteCelebrity(celebrity : Celebrity){
        celebrityDao.deleteCelebrity(celebrity)
    }

}