package com.example.imagesapp.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImagesDao {
    @Query("SELECT * FROM ImagesDetails ORDER BY ID ASC")
    fun getImages(): LiveData<List<ImagesDetails>>

    @Insert
    fun addImage(newImage: ImagesDetails)

    @Delete
    fun deleteImage(Image: ImagesDetails)
}