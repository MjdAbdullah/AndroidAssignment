package com.example.headsup_room.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CelebrityDao {
    @Query("SELECT * FROM Celebrity ORDER BY Id ASC")
    fun getCelebrity(): LiveData<List<Celebrity>>

    @Insert
    fun addCelebrity(newCelebrity: Celebrity)

    @Delete
    fun deleteCelebrity(celebrity: Celebrity)

    @Update
    fun updateCelebrity(celebrity: Celebrity)

}