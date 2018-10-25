package com.j2rvaservud.tluhomeworks.network

import androidx.lifecycle.LiveData
/**
 * Created by ahmedrizwan on 9/9/17.
 * Helper class for transmitting an empty LiveData - Pretty useful!
 */
class AbsentLiveData<T> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {

            return AbsentLiveData()
        }
    }
}
