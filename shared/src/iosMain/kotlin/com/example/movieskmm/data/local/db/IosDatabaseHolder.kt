package com.example.movieskmm.data.local.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import data.local.db.AppDatabaseQueries

class IosDatabaseHolder(
    key: String? = null,
    reKey: String? = null,
    name: String = DbHelper.DB_NAME,
    schema: SqlSchema<QueryResult.Value<Unit>> = AppDatabase.Schema
) : DatabaseHolder() {
    private val configuration = createDatabaseConfiguration(name, schema, key, reKey)
    override val driver: SqlDriver = NativeSqliteDriver(configuration)
    override val appDatabase: AppDatabase = createQueryWrapper(driver)
    override val appDatabaseQueries: AppDatabaseQueries = appDatabase.appDatabaseQueries

    override fun close() = driver.close()

    companion object {
        fun createDatabaseConfiguration(
            name: String = DbHelper.DB_NAME,
            schema: SqlSchema<QueryResult.Value<Unit>> = AppDatabase.Schema,
            key: String? = null,
            reKey: String? = null
        ): DatabaseConfiguration = DatabaseConfiguration(
            name = name,
            version = schema.version.toInt(),
            create = { connection ->
                wrapConnection(connection, schema::create)
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) {
                    schema.migrate(it, oldVersion.toLong(), newVersion.toLong())
                }
            },
            encryptionConfig = DatabaseConfiguration.Encryption(key, reKey)
        )
    }
}