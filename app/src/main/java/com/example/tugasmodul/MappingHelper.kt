package com.example.tugasmodul

import android.database.Cursor
import com.example.tugasmodul.database.DatabaseContract

object MappingHelper {
    /**
     * Mengubah Cursor menjadi ArrayList berisi objek Homework
     */
    fun mapCursorToArrayList(homeworkCursor: Cursor?): ArrayList<Homework> {
        val homeworkList = ArrayList<Homework>()

        // Mengecek apakah Cursor tidak null
        homeworkCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.HomeworkColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.HomeworkColumns.TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.HomeworkColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.HomeworkColumns.DATE))

                // Menambahkan data ke dalam list
                homeworkList.add(Homework(id, title, description, date))
            }
        }

        return homeworkList
    }
}
