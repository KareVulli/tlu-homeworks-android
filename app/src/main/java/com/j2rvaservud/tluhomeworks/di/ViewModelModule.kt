package com.j2rvaservud.tluhomeworks.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.j2rvaservud.tluhomeworks.viewmodel.RepoViewModel
import com.j2rvaservud.tluhomeworks.viewmodel.UserLoginViewModel
import com.j2rvaservud.tluhomeworks.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    internal abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserLoginViewModel::class)
    internal abstract fun bindUserLoginViewModel(userLoginViewModel: UserLoginViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}