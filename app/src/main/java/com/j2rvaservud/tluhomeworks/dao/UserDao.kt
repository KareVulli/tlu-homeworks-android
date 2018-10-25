package com.j2rvaservud.tluhomeworks.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.j2rvaservud.tluhomeworks.model.User



@Dao
abstract class UserDao {

    @Query("SELECT * FROM User")
    abstract fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(repositories: List<User>)

}