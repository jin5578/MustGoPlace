package com.example.mustgoplace.ui.revise

import com.example.mustgoplace.di.qualifier.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ReviseModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesReviseFragment(): ReviseFragment
}
