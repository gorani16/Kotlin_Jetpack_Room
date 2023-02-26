package com.gorani.jetpack_room.repository

import android.content.Context
import com.gorani.jetpack_room.database.TextDatabase
import com.gorani.jetpack_room.entity.TextEntity
import com.gorani.jetpack_room.entity.WordEntity

class Repository(context: Context) {

    private val db = TextDatabase.getDatabase(context)

    fun getTextList() = db.textDao().getAllData()

    fun getWordList() = db.wordDao().getAllData()

    fun insertTextData(text: String) = db.textDao().insert(TextEntity(0, text))

    fun insertWordData(text: String) = db.wordDao().insert(WordEntity(0, text))

    fun deleteTextData() = db.textDao().deleteAllData()

    fun deleteWordData() = db.wordDao().deleteAllData()

}