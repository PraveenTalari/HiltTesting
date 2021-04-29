package com.example.hilttesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.hilttesting.IDependencyProvidedByHiltModule
import com.example.hilttesting.MainActivity
import com.example.hilttesting.ProductionHiltModule
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

    @Module
    @InstallIn(SingletonComponent::class)
    object TestModule {
        @Provides
        fun provideMessageService() = object : IDependencyProvidedByHiltModule {
            override fun getMessage(): String {
                return "Test DependencyProvidedByHiltModule"
            }
        }
    }

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun exampleForTestModuleReplacement() {
        signUpTestRule.onNodeWithText("Test DependencyProvidedByHiltModule").assertIsDisplayed()
    }
}
