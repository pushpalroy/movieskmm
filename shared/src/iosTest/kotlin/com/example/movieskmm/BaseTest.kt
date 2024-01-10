package com.example.movieskmm

import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.data.local.db.IosCipherUtils
import com.example.movieskmm.data.local.db.IosDbHelper

actual abstract class BaseTest actual constructor() {

    actual val dbHelper: DbHelper = IosDbHelper()

    actual fun deleteDb() {
        IosCipherUtils.deleteDatabase()
    }
}