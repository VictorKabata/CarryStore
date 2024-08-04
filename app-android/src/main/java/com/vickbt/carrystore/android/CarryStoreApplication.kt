package com.vickbt.carrystore.android

import android.app.Application
import com.vickbt.carrystore.utils.DiHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class CarryStoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DiHelper(appDeclaration = {
            androidLogger(level = Level.DEBUG)
            androidContext(this@CarryStoreApplication)
        }).initKoin()
    }
}
