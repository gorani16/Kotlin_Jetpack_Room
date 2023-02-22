package com.gorani.jetpack_room.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.gorani.jetpack_room.R

/**
 * Room
 * https://developer.android.com/training/data-storage/room?hl=ko
 *
 * SQLite
 * https://developer.android.com/training/data-storage/sqlite?hl=ko
 */

class SqliteActivity : AppCompatActivity() {

    private lateinit var db: SQLiteHelperSample

    private val inputArea: EditText by lazy {
        findViewById(R.id.et_input)
    }

    private val insertBtn: Button by lazy {
        findViewById(R.id.btn_insert)
    }

    private val selectAllBtn: Button by lazy {
        findViewById(R.id.btn_select_all)
    }

    private val deleteBtn: Button by lazy {
        findViewById(R.id.btn_delete_all)
    }

    private val resultArea: TextView by lazy {
        findViewById(R.id.tv_result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        db = SQLiteHelperSample(this)

        insertBtn.setOnClickListener {
            val inputText = inputArea.text.toString()
            db.insert(inputText)
            inputArea.setText("")
        }

        selectAllBtn.setOnClickListener {
            val dbData = db.getAllData()
            resultArea.text = dbData.toString()
        }

        deleteBtn.setOnClickListener {
            db.deleteAll()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

}
