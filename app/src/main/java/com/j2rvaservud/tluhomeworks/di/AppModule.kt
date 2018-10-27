package com.j2rvaservud.tluhomeworks.di

import android.app.Application
import androidx.room.Room
import com.j2rvaservud.tluhomeworks.dao.GroupDao
import com.j2rvaservud.tluhomeworks.dao.UserDao
import com.j2rvaservud.tluhomeworks.dao.UserTokenDao
import com.j2rvaservud.tluhomeworks.db.AppDb
import com.j2rvaservud.tluhomeworks.db.RepoDao
import com.j2rvaservud.tluhomeworks.retrofit.LiveDataCallAdapterFactory
import com.j2rvaservud.tluhomeworks.retrofit.WebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 */
@Module(includes = [ViewModelModule::class])
internal class AppModule {

    @Singleton
    @Provides
    fun provideGithubService(): WebService {
        return Retrofit.Builder()
                .baseUrl("https://tlu.j2rvaservud.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, "app-db")
                .fallbackToDestructiveMigration()
                .build()
    }


    @Singleton
    @Provides
    fun provideRepoDao(db: AppDb): RepoDao {
        return db.repoDao()
    }

    @Singleton
    @Provides
    fun provideGroupDao(db: AppDb): GroupDao {
        return db.groupDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDb): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideUserTokenDao(db: AppDb): UserTokenDao {
        return db.userTokenDao()
    }

}