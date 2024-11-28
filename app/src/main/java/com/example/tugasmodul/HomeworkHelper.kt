package com.example.tugasmodul

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.tugasmodul.database.DatabaseContract.HomeworkColumns.Companion._ID
import com.example.tugasmodul.database.DatabaseHelper
import java.sql.SQLException
import kotlin.jvm.Throws

class HomeworkHelper(context: Context) {

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = "homework"
        private var INSTANCE: HomeworkHelper? = null

        fun getInstance(context: Context): HomeworkHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HomeworkHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()
        if (database.isOpen) {
            database.close()
        }
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC",
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = ?", arrayOf(id))
    }
}
