package com.example.mustgoplace.ui.search

import com.example.mustgoplace.di.qualifier.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class SearchModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesSearchFragment(): SearchFragment
}
