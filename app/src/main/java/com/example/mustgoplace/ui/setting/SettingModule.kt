package com.example.mustgoplace.ui.setting

import com.example.mustgoplace.di.qualifier.FragmentScope
import com.example.mustgoplace.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class SettingModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesSettingFragment(): HomeFragment
}