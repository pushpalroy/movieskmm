package com.example.movieskmm

import com.example.movieskmm.data.local.db.DbHelper
import com.example.movieskmm.db.JvmTestDbHelper

actual abstract class BaseTest {

    actual val dbHelper: DbHelper = JvmTestDbHelper()

    actual fun deleteDb() {
        TODO()
    }
}
