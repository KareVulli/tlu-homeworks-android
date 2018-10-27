package com.j2rvaservud.tluhomeworks.repository

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import com.j2rvaservud.tluhomeworks.dao.GroupDao
import com.j2rvaservud.tluhomeworks.model.Group
import com.j2rvaservud.tluhomeworks.network.ApiResponse
import com.j2rvaservud.tluhomeworks.network.NetworkBoundResource
import com.j2rvaservud.tluhomeworks.network.Resource
import com.j2rvaservud.tluhomeworks.retrofit.WebService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepository
@Inject constructor(val groupDao: GroupDao, val webService: WebService) {

    fun getGroups(): LiveData<Resource<List<Group>>> {
        return object : NetworkBoundResource<List<Group>, List<Group>>() {
            override fun saveCallResult(item: List<Group>) {
                groupDao.insertAll(item)
            }

            override fun shouldFetch(@Nullable data: List<Group>?): Boolean {
                return data == null //|| data.isEmpty() || repoListRateLimit.shouldFetch(owner)
            }

            override fun loadFromDb(): LiveData<List<Group>> {
                return groupDao.getAll()
            }

            override fun createCall(): LiveData<ApiResponse<List<Group>>> {
                return webService.getGroups()
            }

            override fun onFetchFailed() {
                //repoListRateLimit.reset(userCredentials)
            }
        }.asLiveData()
    }
}