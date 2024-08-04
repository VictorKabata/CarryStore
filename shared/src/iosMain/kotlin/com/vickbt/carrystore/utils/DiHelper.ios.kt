package com.vickbt.carrystore.utils

import com.vickbt.carrystore.di.commonModule
import com.vickbt.carrystore.di.platformModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

actual class DiHelper {
    actual fun initKoin(): KoinApplication = startKoin{
        modules(commonModule, platformModule())
    }
}