package com.example.imagesapp.Data

import androidx.lifecycle.LiveData

class ImagesRepository(private val imagesDao: ImagesDao) {

    val getImages: LiveData<List<ImagesDetails>> = imagesDao.getImages()

    suspend fun addImage(image: ImagesDetails){
        imagesDao.addImage(image)
    }

    suspend fun deleteImage(image: ImagesDetails){
        imagesDao.deleteImage(image)
    }

}