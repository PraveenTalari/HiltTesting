package com.example.hilttesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.hilttesting.ui.theme.HiltTestingTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var unChangedDependencyProvidedByHiltModule: UnChangedDependency

    @Inject
    lateinit var dependencyProvidedByHiltModule: IDependencyProvidedByHiltModule

    @Inject
    lateinit var openClassWithoutInterface: OpenClassWithoutInterface

    @Inject
    lateinit var finalClassWithoutInterface: FinalClassWithoutInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Greeting(unChangedDependencyProvidedByHiltModule.getMessage())
                        Greeting(dependencyProvidedByHiltModule.getMessage())
                        Greeting(openClassWithoutInterface.getMessage())
                        Greeting(finalClassWithoutInterface.getMessage())
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = name)
}

interface IDependencyProvidedByHiltModule {
    fun getMessage(): String
}

//imagine this is some class to which we cannot add "@Inject constructor"
class DependencyProvidedByHiltModule : IDependencyProvidedByHiltModule {
    override fun getMessage(): String {
        return "Production"
    }
}
