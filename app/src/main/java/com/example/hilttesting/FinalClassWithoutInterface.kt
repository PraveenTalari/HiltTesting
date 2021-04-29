package com.example.hilttesting

import javax.inject.Inject

@AnnotationToMakeClassOpenForTesting
class FinalClassWithoutInterface @Inject constructor() {
    fun getMessage(): String {
        return "Production SomeFinalClassWithoutInterface"
    }
}