package com.vickbt.carrystore.android

import android.app.Application
import com.vickbt.carrystore.utils.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class CarryStoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@CarryStoreApplication)
        }
    }
}
