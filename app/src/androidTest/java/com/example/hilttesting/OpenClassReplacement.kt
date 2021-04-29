package com.example.hilttesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.hilttesting.MainActivity
import com.example.hilttesting.OpenClassWithoutInterface
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class OpenClassReplacement {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var signUpTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @BindValue
    @JvmField
    //@AnnotationToMakeClassOpenForTesting annotation is used to make Dependency class open
    var dependency: OpenClassWithoutInterface = FinalClassSample()

    class FinalClassSample : OpenClassWithoutInterface() {
        override fun getMessage(): String {
            return TEST_MESSAGE
        }
    }

    @Test
    fun exampleForTestModuleReplacement() {
        signUpTestRule.onNodeWithText(TEST_MESSAGE).assertIsDisplayed()
    }

    companion object {
        const val TEST_MESSAGE = "Test OpenClassWithoutInterface"
    }
}
