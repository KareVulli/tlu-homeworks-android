package com.j2rvaservud.tluhomeworks.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.j2rvaservud.tluhomeworks.dao.UserDao
import com.j2rvaservud.tluhomeworks.dao.UserTokenDao
import com.j2rvaservud.tluhomeworks.model.Repo
import com.j2rvaservud.tluhomeworks.model.User
import com.j2rvaservud.tluhomeworks.model.UserToken

/**
 * Created by ahmedrizwan on 9/9/17.
 * Database Class including the Dao
 * TODO: Change the database & dao based on what you want
 */
@Database(entities = [Repo::class, User::class, UserToken::class], version = 2, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun userDao(): UserDao
    abstract fun userTokenDao(): UserTokenDao
}

@Dao
abstract class RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repositories: List<Repo>)

    @Query("SELECT * FROM Repo "
            + "WHERE lower(owner_login) = lower(:owner)"
            + "ORDER BY stars DESC")
    abstract fun loadRepositories(owner: String): LiveData<List<Repo>>

}