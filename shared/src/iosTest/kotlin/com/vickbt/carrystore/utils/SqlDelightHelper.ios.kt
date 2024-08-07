package com.vickbt.carrystore.utils

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.inMemoryDriver
import com.vickbt.shared.data.cache.sqldelight.AppDatabase

internal actual fun createTestDriver(): SqlDriver {
    return inMemoryDriver(AppDatabase.Schema)
}
