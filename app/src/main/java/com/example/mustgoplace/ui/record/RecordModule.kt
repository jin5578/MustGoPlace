package com.example.mustgoplace.ui.record

import com.example.mustgoplace.di.qualifier.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class RecordModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesRecordFragment(): RecordFragment
}
