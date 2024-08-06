package com.vickbt.carrystore.utils

import org.koin.core.KoinApplication

/*
class DiHelper {
    fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(commonModule, platformModule())
    }
}*/

expect class DiHelper {
    fun initKoin(): KoinApplication
}
