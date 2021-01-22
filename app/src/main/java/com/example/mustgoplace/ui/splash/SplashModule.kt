package com.example.mustgoplace.ui.splash

import com.example.mustgoplace.di.qualifier.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class SplashModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesSplashFragment(): SplashFragment
}