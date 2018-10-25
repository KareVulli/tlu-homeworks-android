package com.j2rvaservud.tluhomeworks.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.j2rvaservud.tluhomeworks.model.UserCredentials
import com.j2rvaservud.tluhomeworks.model.UserToken
import com.j2rvaservud.tluhomeworks.network.AbsentLiveData
import com.j2rvaservud.tluhomeworks.network.Resource
import com.j2rvaservud.tluhomeworks.repository.UserRepository
import javax.inject.Inject


class UserLoginViewModel
@Inject constructor(userRepository: UserRepository) : ViewModel() {

    val token: LiveData<Resource<UserToken>>
    private val query: MutableLiveData<UserCredentials> = MutableLiveData()

    init {
        token = Transformations.switchMap(query) {
            Log.d("Test", "switchmap")
            when {
                it == null || it.username.isEmpty() || it.password.isEmpty() -> AbsentLiveData.create()
                else -> userRepository.loginUser(it)
            }
        }
    }

    fun setQuery(username: String, password: String) {
        Log.d("Test", "setQuery()")
        query.value = UserCredentials(username, password)
    }

}