package com.j2rvaservud.tluhomeworks.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.j2rvaservud.tluhomeworks.model.Group

@Dao
abstract class GroupDao {

    @Query("SELECT * FROM `Group`")
    abstract fun getAll(): LiveData<List<Group>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(repositories: List<Group>)

}