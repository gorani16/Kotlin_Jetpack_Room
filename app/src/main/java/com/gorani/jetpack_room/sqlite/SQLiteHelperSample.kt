package com.gorani.jetpack_room.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperSample(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "myTestDB.db"
        private const val TBL_NAME = "my_table"

        private const val ID = "id"
        private const val TITLE = "title"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val SQL_CREATE_ENTRISE =
            "CREATE TABLE $TBL_NAME (" +
                    "$ID INTEGER PRIMARY KEY," +
                    "$TITLE TEXT)"

        db?.execSQL(SQL_CREATE_ENTRISE)
    }

    // Data 삽입
    fun insert(str: String) {

        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(TITLE, str)
        }

        db.insert(TBL_NAME, null, values)

    }

    // Data 불러오기
    fun getAllData() : ArrayList<String> {

        val db = this.readableDatabase
        val query = "SELECT * FROM $TBL_NAME"

        val cursor = db.rawQuery(query, null)

        val arr = ArrayList<String>()

        with(cursor) {
            while (moveToNext()) {
                arr.add(getString(1))   // getString(0) = ID, getString(1) = TITLE
            }
        }
        return arr

    }

    fun deleteAll() {

        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TBL_NAME")

    }

    // DATABASE VERSION 변동 시 사용하는 함수 : 이전 버전의 DB 는 삭제하고 새로운 버전의 DB 를 생성함.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBL_NAME")
        onCreate(db)
    }
}