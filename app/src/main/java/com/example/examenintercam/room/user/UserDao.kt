package com.example.examenintercam.room.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Query("SELECT * FROM tbl_user")
    fun getAllUser(): List<UserEntity>

    @Query("SELECT EXISTS(SELECT * FROM tbl_user WHERE email == :emailUser AND password == :psw)")
    fun isUserExist(emailUser: String, psw: String): Boolean

}