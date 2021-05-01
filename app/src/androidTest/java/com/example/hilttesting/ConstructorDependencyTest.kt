package com.example.hilttesting

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * This is to show that classes with constructor injection can be
 * obtained using Hilt
 */
@HiltAndroidTest
class ConstructorDependencyTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    //we don't require Activity rule as
    //FinalClassWithoutInterface is not from from a module which has

    @Inject
    lateinit var finalClassWithoutInterface: FinalClassWithoutInterface

    @Before
    fun setUp() {
        //not doing in before function as we want to check the functionality of hiltRule.inject in tests
        //hiltRule.inject()
    }

    @Test
    fun testDependencyIsNullWithoutHiltInject() {
        assertFalse(this::finalClassWithoutInterface.isInitialized)
        hiltRule.inject()
        assertTrue(this::finalClassWithoutInterface.isInitialized)
    }

    @Test
    fun testThatWeGetProductionDependency() {
        hiltRule.inject()
        //below will fail without the above line "hiltRule.inject()"
        assertEquals(PROD_MESSAGE, finalClassWithoutInterface.getMessage())
    }

    companion object {
        const val PROD_MESSAGE = "Production SomeFinalClassWithoutInterface"
    }
}
