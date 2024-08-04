package com.vickbt.carrystore.android

import android.app.Application
import com.vickbt.carrystore.di.commonModule
import com.vickbt.carrystore.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CarryStoreApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@CarryStoreApplication)
            modules(commonModule, platformModule())
        }
    }

}