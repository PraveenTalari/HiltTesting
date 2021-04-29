package com.example.hilttesting

import javax.inject.Inject

open class OpenClassWithoutInterface @Inject constructor() {
    open fun getMessage(): String {
        return "Production OpenClassWithoutInterface"
    }
}