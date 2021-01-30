package com.example.mustgoplace.di

import com.example.mustgoplace.di.qualifier.ActivityScope
import com.example.mustgoplace.ui.home.HomeModule
import com.example.mustgoplace.ui.main.MainActivity
import com.example.mustgoplace.ui.main.MainModule
import com.example.mustgoplace.ui.record.RecordModule
import com.example.mustgoplace.ui.revise.ReviseModule
import com.example.mustgoplace.ui.setting.SettingModule
import com.example.mustgoplace.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ViewBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            SplashModule::class,
            HomeModule::class,
            SettingModule::class,
            RecordModule::class,
            ReviseModule::class
        ]
    )

    abstract fun contributesMainActivity(): MainActivity
}