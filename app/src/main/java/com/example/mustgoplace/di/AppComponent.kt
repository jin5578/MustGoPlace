package com.example.mustgoplace.di

import com.example.mustgoplace.MustGoPlaceApp
import com.example.mustgoplace.data.db.DatabaseModule
import com.example.mustgoplace.data.repository.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppAssistedInjectModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewBindingModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent : AndroidInjector<MustGoPlaceApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MustGoPlaceApp): AppComponent
    }
}