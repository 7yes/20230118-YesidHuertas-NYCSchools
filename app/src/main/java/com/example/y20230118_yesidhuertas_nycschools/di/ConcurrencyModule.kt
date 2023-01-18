package com.example.y20230118_yesidhuertas_nycschools.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
class ConcurrencyModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class IODispatcher

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DefaultDispatcher

    @IODispatcher
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher =
        Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher =
        Dispatchers.Default
}