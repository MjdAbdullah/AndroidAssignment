package com.example.imagesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.imagesapp.Data.ImagesDao
import com.example.imagesapp.Data.ImagesDatabase
import com.example.imagesapp.Data.ImagesDetails
import com.example.imagesapp.Data.ImagesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class myViewModel(application : Application): AndroidViewModel(application) {

    private val repository: ImagesRepository
    private val images: LiveData<List<ImagesDetails>>

    init {
        val imageDao = ImagesDatabase.getinstance(application).ImagesDao()
        repository = ImagesRepository(imageDao)
        images = repository.getImages
    }

    fun getImages(): LiveData<List<ImagesDetails>> {
        return images as LiveData<List<ImagesDetails>>
    }

    fun addImages(newImage: ImagesDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addImage(newImage)
        }
    }

    fun deleteImages(selectImage: ImagesDetails){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteImage(selectImage)
        }
    }

}