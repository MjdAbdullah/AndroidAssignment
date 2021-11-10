package com.example.headsup_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.headsup_room.Data.Celebrity
import com.example.headsup_room.Data.CelebrityDatabase
import com.example.headsup_room.Data.CelebrityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class myViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CelebrityRepository
    private val celebrity: LiveData<List<Celebrity>>
    init {
        val celebrityDao = CelebrityDatabase.getinstance(application).CelebrityDao()
        repository = CelebrityRepository(celebrityDao)
        celebrity = repository.getCelebrity
    }

    fun getCelebrity(): LiveData<List<Celebrity>> {
        return celebrity as LiveData<List<Celebrity>>
    }

    fun addCelebrity(new: Celebrity){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addCelebrity(new)
        }
    }

    fun updateCelebrity(SelectCelebrity: Celebrity){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateCelebrity(SelectCelebrity)
        }
    }

    fun deleteCelebrity(SelectCelebrity: Celebrity){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteCelebrity(SelectCelebrity)
        }
    }
}