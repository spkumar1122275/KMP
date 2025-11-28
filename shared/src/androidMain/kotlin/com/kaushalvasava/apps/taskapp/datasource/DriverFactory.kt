package com.kaushalvasava.apps.taskapp.datasource

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kaushalvasava.apps.taskapp.TaskDatabase

actual class DriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = TaskDatabase.Schema,
            context = context,
            name = "TaskDatabase.db",
            callback = object : AndroidSqliteDriver.Callback(TaskDatabase.Schema) {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // New installs: tables are created automatically by SQLDelight schema
                }

                override fun onUpgrade(
                    db: SupportSQLiteDatabase,
                    oldVersion: Int,
                    newVersion: Int
                ) {
                    super.onUpgrade(db, oldVersion, newVersion)

                    // Migration for Company table
                    if (oldVersion < 2) { // Increment version if needed
                        db.execSQL("""
                            CREATE TABLE IF NOT EXISTS Company (
                                store_id INTEGER NOT NULL PRIMARY KEY,
                                company_id INTEGER,
                                company_name TEXT,
                                address TEXT,
                                phone TEXT,
                                tax_id TEXT
                            )
                        """.trimIndent())
                    }
                }
            }
        )
    }
}
