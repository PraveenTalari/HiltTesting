/*
package com.example.hilttesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(ProductionHiltModule::class)
class HiltTestModule {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var signUpTestRule = createAndroidComposeRule<MainActivity>()

    //below code have 2 issues
    //1. overriding getUnChangedDependency even though we don't care about it
    //2. TestModule will be replaced for all tests of test class(no for only required tests)
    @Module
    @InstallIn(SingletonComponent::class)
    object TestModule {
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

    */
/*
    //the above code can be simplified using delegation(but it needs interface)
    //but it will NOT work as we cannot pass parameters to Hilt modules
    @Module
    @InstallIn(SingletonComponent::class)
    class TestModule(b: ProductionHiltModule) : MarkerInterfaceForModule by b {
        @Provides
        fun provideMessageService() = object : IDependencyProvidedByHiltModule {
            override fun getMessage(): String {
                return "Test DependencyProvidedByHiltModule"
            }
        }
    }*//*


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun exampleForTestModuleReplacement() {
        signUpTestRule.onNodeWithText("Test DependencyProvidedByHiltModule").assertIsDisplayed()
    }
}
*/
