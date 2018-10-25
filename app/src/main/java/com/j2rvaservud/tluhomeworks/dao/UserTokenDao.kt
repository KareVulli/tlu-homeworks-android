package com.j2rvaservud.tluhomeworks.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.j2rvaservud.tluhomeworks.model.UserToken

@Dao
abstract class UserTokenDao {

    @Query("SELECT * FROM UserToken LIMIT 1")
    abstract fun getToken(): LiveData<UserToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userToken: UserToken)
}