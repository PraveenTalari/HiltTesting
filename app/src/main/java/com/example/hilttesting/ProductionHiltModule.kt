package com.example.hilttesting

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//interface just to use delegation
interface MarkerInterfaceForModule

@Module
@InstallIn(SingletonComponent::class)
object ProductionHiltModule : MarkerInterfaceForModule {
    @Provides
    fun provideMessageService(): IDependencyProvidedByHiltModule = DependencyProvidedByHiltModule()

    @Provides
    fun getUnChangedDependency(): UnChangedDependency {
        return UnChangedDependency()
    }
}

class UnChangedDependency {
    fun getMessage(): String {
        return "UnChangedDependency"
    }
}
