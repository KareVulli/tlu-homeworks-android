package com.j2rvaservud.tluhomeworks.di

import com.j2rvaservud.tluhomeworks.fragments.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment

}