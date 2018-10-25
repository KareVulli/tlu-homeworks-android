package com.j2rvaservud.tluhomeworks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.j2rvaservud.tluhomeworks.model.Repo
import com.j2rvaservud.tluhomeworks.network.AbsentLiveData
import com.j2rvaservud.tluhomeworks.network.Resource
import com.j2rvaservud.tluhomeworks.repository.RepoRepository
import java.util.*
import javax.inject.Inject


/**
 * Created by ahmedrizwan on 9/10/17.
 * ViewModel for the Repos
 * TODO: Change/Add/Remove ViewModels in this package!
 */
class RepoViewModel
@Inject constructor(repository: RepoRepository) : ViewModel() {
//    val repoRepository = repository
//    val map: ArrayMap<String, LiveData<Resource<List<Repo>>>> = ArrayMap()
    var currentRepoUser: String? = null

    //live data of list of Repos, called results
    val results: LiveData<Resource<List<Repo>>>
    private val query: MutableLiveData<String> = MutableLiveData()

    init {
        results = Transformations.switchMap(query, {
            when {
                it == null || it.length == 1 -> AbsentLiveData.create()
                else -> repository.loadRepos(it)
            }
        })
    }

    fun setQuery(originalInput: String?, force:Boolean) {
        if(originalInput==null) return
        val input = originalInput.toLowerCase(Locale.getDefault()).trim { it <= ' ' }
        if (input == query.value && !force) {
            return
        }
        query.value = input
    }

}