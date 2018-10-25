package com.j2rvaservud.tluhomeworks.repository

import androidx.annotation.Nullable
import com.j2rvaservud.tluhomeworks.model.User
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.j2rvaservud.tluhomeworks.dao.UserTokenDao
import com.j2rvaservud.tluhomeworks.db.RepoDao
import com.j2rvaservud.tluhomeworks.model.Repo
import com.j2rvaservud.tluhomeworks.model.UserCredentials
import com.j2rvaservud.tluhomeworks.model.UserToken
import com.j2rvaservud.tluhomeworks.network.ApiResponse
import com.j2rvaservud.tluhomeworks.network.NetworkBoundResource
import com.j2rvaservud.tluhomeworks.network.Resource
import com.j2rvaservud.tluhomeworks.retrofit.WebService
import com.j2rvaservud.tluhomeworks.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject constructor(val userTokenDao: UserTokenDao, val webService: WebService) {

    fun loginUser(userCredentials: UserCredentials): LiveData<Resource<UserToken>> {
        return object : NetworkBoundResource<UserToken, UserToken>() {
            override fun saveCallResult(item: UserToken) {
                userTokenDao.insert(item)
            }

            override fun shouldFetch(@Nullable data: UserToken?): Boolean {
                return data == null //|| data.isEmpty() || repoListRateLimit.shouldFetch(owner)
            }

            override fun loadFromDb(): LiveData<UserToken> {
                return userTokenDao.getToken()
            }

            override fun createCall(): LiveData<ApiResponse<UserToken>> {
                return webService.loginUser(userCredentials)
            }

            override fun onFetchFailed() {
                //repoListRateLimit.reset(userCredentials)
            }
        }.asLiveData()
    }
}