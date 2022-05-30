package com.example.examenintercam.room.beer

import androidx.room.*

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(beer: BeerEntity)

    @Update
    fun update(beer: BeerEntity)

    @Query("SELECT * FROM tbl_beer")
    fun getAllBeer(): List<BeerEntity>

}