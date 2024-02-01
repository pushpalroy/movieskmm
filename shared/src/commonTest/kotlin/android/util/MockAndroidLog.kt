package android.util

import kotlin.jvm.JvmStatic

class Log {
    fun d(tag: String, msg: String): Int {
        println("DEBUG: $tag: $msg")
        return 0
    }

    fun i(tag: String, msg: String): Int {
        println("INFO: $tag: $msg")
        return 0
    }

    fun w(tag: String, msg: String): Int {
        println("WARN: $tag: $msg")
        return 0
    }

    fun e(tag: String, msg: String): Int {
        println("ERROR: $tag: $msg")
        return 0
    }

    companion object {
        @JvmStatic
        fun println(i: Int, tag: String, msg: String): Int {
            println("Printing: $i: $tag: $msg")
            return 0
        }
    }
}