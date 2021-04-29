package com.example.hilttesting

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProductionHiltModule {
    @Provides
    fun provideMessageService(): IDependencyProvidedByHiltModule = DependencyProvidedByHiltModule()
}
