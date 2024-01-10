package com.example.movieskmm

import com.example.movieskmm.data.local.db.DbHelper

expect abstract class BaseTest() {

    val dbHelper: DbHelper

    fun deleteDb()
}
