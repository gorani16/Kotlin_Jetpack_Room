package com.gorani.jetpack_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Room 이란?
 * https://developer.android.com/training/data-storage/room?hl=ko
 *
 * Room 사용법
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=ko#0
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = TextDatabase.getDatabase(this)


        CoroutineScope(Dispatchers.IO).launch {
            db.textDao().insert(TextEntity(0,"Hello"))
            val textData = db.textDao().getAllData()
            Log.d("textData", textData.toString())
        }

    }

}
