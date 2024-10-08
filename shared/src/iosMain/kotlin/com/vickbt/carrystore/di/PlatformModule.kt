package com.vickbt.carrystore.di

import com.vickbt.carrystore.utils.DatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory().createDriver() }
}
