package com.j2rvaservud.tluhomeworks.retrofit

import androidx.lifecycle.LiveData
import com.j2rvaservud.tluhomeworks.model.Group
import com.j2rvaservud.tluhomeworks.model.Repo
import com.j2rvaservud.tluhomeworks.model.UserCredentials
import com.j2rvaservud.tluhomeworks.model.UserToken
import com.j2rvaservud.tluhomeworks.network.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by ahmedrizwan on 9/9/17.
 * Retrofit Service class
 */
interface WebService {

    @POST("login_check")
    fun loginUser(@Body credentials: UserCredentials): LiveData<ApiResponse<UserToken>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repo>>>

    @GET("groups")
    fun getGroups(): LiveData<ApiResponse<List<Group>>>

}