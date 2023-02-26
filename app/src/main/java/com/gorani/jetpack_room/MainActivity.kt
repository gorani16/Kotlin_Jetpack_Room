package com.gorani.jetpack_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.gorani.jetpack_room.database.TextDatabase
import com.gorani.jetpack_room.entity.TextEntity
import com.gorani.jetpack_room.entity.WordEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Room 이란?
 * https://developer.android.com/training/data-storage/room?hl=ko
 *
 * Room 사용법
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=ko#0
 *
 * Coroutine
 * - 코루틴은 스레드 안에서 실행되는 "일시 중단 가능한 작업의 단위이다."
 * - 하나의 스레드에 여러 코루틴이 존재할 수 있다.
 * https://developer.android.com/kotlin/coroutines/coroutines-adv?hl=ko
 * https://kotlinworld.com/139?category=973476
 *
 * Dispatchers
 * - Dispatch : 보내다.
 * ㄴ> 스레드에 코루틴을 보낸다. 코루틴에서는 스레드 풀을 만들고 Dispatcher 를 통해서 코루틴을 배분한다.
 * 즉, 코루틴을 만든 다음 해당 코루틴을 Dispatcher 에 전송하면 Dispatcher 은 자신이 관리하는 스레드풀 내의
 * 스레드의 부하 상황에 맞춰 코루틴을 배분한다.
 * - 스레드를 배치하여 관리해주는것.
 * https://kotlinworld.com/141?category=973476
 * https://developer.android.com/kotlin/coroutines/coroutines-adv?hl=ko
 *
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

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getData()

        btnInsert.setOnClickListener {
            val text = inputArea.text.toString()
            viewModel.insertData(text)
            inputArea.setText("")
        }

        btnGetData.setOnClickListener {
            viewModel.getData()
            Log.d("ActivityData : ", viewModel.getData().toString())
        }

        btnDelete.setOnClickListener {
            viewModel.removeData()
        }

    }

}
