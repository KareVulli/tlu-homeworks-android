package com.j2rvaservud.tluhomeworks.repository

import androidx.lifecycle.LiveData
import androidx.annotation.Nullable
import com.j2rvaservud.tluhomeworks.db.RepoDao
import com.j2rvaservud.tluhomeworks.model.Repo
import com.j2rvaservud.tluhomeworks.network.ApiResponse
import com.j2rvaservud.tluhomeworks.network.NetworkBoundResource
import com.j2rvaservud.tluhomeworks.network.Resource
import com.j2rvaservud.tluhomeworks.retrofit.WebService
import com.j2rvaservud.tluhomeworks.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ahmedrizwan on 9/10/17.
 * Repository class - uses NetworkBoundResource to load data from API
 * TODO: Change/Add/Remove Repository files in this package
 */
@Singleton
class RepoRepository
@Inject constructor(val repoDao: RepoDao, val webService: WebService) {
    val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun loadRepos(owner: String): LiveData<Resource<List<Repo>>> {
        return object : NetworkBoundResource<List<Repo>, List<Repo>>() {
            override fun saveCallResult(item: List<Repo>) {
                repoDao.insertRepos(item)
            }

            override fun shouldFetch(@Nullable data: List<Repo>?): Boolean {
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(owner)
            }

            override fun loadFromDb(): LiveData<List<Repo>> {
                return repoDao.loadRepositories(owner)
            }

            override fun createCall(): LiveData<ApiResponse<List<Repo>>> {
                return webService.getRepos(owner)
            }

            override fun onFetchFailed() {
                repoListRateLimit.reset(owner)
            }
        }.asLiveData()
    }

}