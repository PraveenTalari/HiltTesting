package com.example.hilttesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.Module
import dagger.Provides
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HiltTestInstallIn {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var signUpTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun exampleForTestModuleReplacement() {
        signUpTestRule.onNodeWithText("Test DependencyProvidedByHiltModule").assertIsDisplayed()
    }
}

//below code have 2 issues
//1. overriding getUnChangedDependency even though we don't care about it
//2. TestModule will be replaced for all tests of test class(no for only required tests)
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ProductionHiltModule::class]
)
object TestInstallInModule {
    @Provides
    fun provideMessageService() = object : IDependencyProvidedByHiltModule {
        override fun getMessage(): String {
            return "Test DependencyProvidedByHiltModule"
        }
    }

    //even though we don't change the below dependency in this test class
    //we have to copy the same from ProductionHiltModule as we are replacing the entire ProductionHiltModule module
    //with TestModule
    @Provides
    fun getUnChangedDependency(): UnChangedDependency {
        return UnChangedDependency()
    }
}

/*
//the above code can be simplified using delegation(but it needs interface)
//but it will NOT work as we cannot pass parameters to Hilt modules
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ProductionHiltModule::class]
)
class TestInstallInModule(b: ProductionHiltModule) : MarkerInterfaceForModule by b {
    @Provides
    fun provideMessageService() = object : IDependencyProvidedByHiltModule {
        override fun getMessage(): String {
            return "Test DependencyProvidedByHiltModule"
        }
    }
}*/
