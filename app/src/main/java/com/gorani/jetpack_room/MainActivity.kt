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

    private val inputArea: EditText by lazy {
        findViewById(R.id.et_input)
    }

    private val btnInsert: Button by lazy {
        findViewById(R.id.btn_insert)
    }

    private val btnGetData: Button by lazy {
        findViewById(R.id.btn_get_data)
    }

    private val btnDelete: Button by lazy {
        findViewById(R.id.btn_delete_all)
    }

    private val resultArea: TextView by lazy {
        findViewById(R.id.tv_result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = TextDatabase.getDatabase(this)

        btnInsert.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.textDao().insert(TextEntity(0,inputArea.text.toString()))
                inputArea.setText("")
            }
        }

        btnGetData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val textData = db.textDao().getAllData()
                Log.d("DB_Data : ", textData.toString())
            }
        }

        btnDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.textDao().deleteAllData()
            }
        }

    }

}
