package com.vickbt.carrystore.utils

import com.vickbt.carrystore.di.commonModule
import com.vickbt.carrystore.di.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule, platformModule())
}