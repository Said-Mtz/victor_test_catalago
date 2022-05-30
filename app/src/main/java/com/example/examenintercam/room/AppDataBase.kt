package com.example.examenintercam.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examenintercam.room.beer.BeerDao
import com.example.examenintercam.room.beer.BeerEntity
import com.example.examenintercam.room.user.UserDao
import com.example.examenintercam.room.user.UserEntity

@Database(entities = [UserEntity::class, BeerEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val beerDao: BeerDao

    companion object{
        const val DATABASE_NAME = "db_catalago"
    }

}