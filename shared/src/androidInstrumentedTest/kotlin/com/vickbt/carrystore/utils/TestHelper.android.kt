package com.vickbt.carrystore.utils

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.vickbt.shared.data.cache.sqldelight.AppDatabase

internal actual fun createTestDriver(): SqlDriver {
    return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also { driver ->
        AppDatabase.Schema.create(driver)
    }
}