package com.example.mustgoplace.ui.home

import com.example.mustgoplace.di.qualifier.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HomeModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment
}
