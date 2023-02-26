package com.gorani.jetpack_room

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gorani.jetpack_room.database.TextDatabase
import com.gorani.jetpack_room.entity.TextEntity
import com.gorani.jetpack_room.entity.WordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel 에서 context 를 필요로 할 때 해결하는 방법
 * https://youngest-programming.tistory.com/327
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private val db = TextDatabase.getDatabase(context)

    private val _textList = MutableLiveData<List<TextEntity>>()
    val textList: LiveData<List<TextEntity>> = _textList

    private val _wordList = MutableLiveData<List<WordEntity>>()
    val wordList: LiveData<List<WordEntity>> = _wordList

    /**
     * Coroutines 의 세 가지 Dispatchers 와 용도
     * Main Thread 에서 Database 에 접근하는 것은 불가능하다. (메인 쓰레드에서 데이터 불러오기)
     * viewModelScope 의 구현부를 보면 기본 실행은 Dispatchers.Main 을 사용한다. => viewModelScope.launch
     * 이 Dispatchers.Main 은 기본 Android Thread 에서 코루틴을 실행한다.
     * 그렇기 때문에 Database 를 불러올 때 에러가 발생했던것이다.
     * Dispatchers.IO 의 경우 기본 Thread 외부에서 디스크 또는 네트워크 I/O 를 실행하도록 최적화 되어있으므로
     * Database 관련 작업을 할때는 이 Dispatchers 를 사용하여 DB 작업을 실행하도록 해야한다. => viewModelScope.launch(Dispatchers.IO)
     * https://developer.android.com/kotlin/coroutines/coroutines-adv?hl=ko
     */

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        val textData = db.textDao().getAllData()
        val wordData = db.wordDao().getAllData()
        Log.d("get_Data : ", textData.toString())
        Log.d("get_Data : ", wordData.toString())

        _textList.postValue(textData)
        _wordList.postValue(wordData)
        Log.d("_textList : ", _textList.value.toString())

    }

    fun insertData(text: String) = viewModelScope.launch(Dispatchers.IO) {
        db.textDao().insert(TextEntity(0, text))
        db.wordDao().insert(WordEntity(0, text))

    }

    fun removeData() = viewModelScope.launch(Dispatchers.IO) {
        db.textDao().deleteAllData()
        db.wordDao().deleteAllData()
    }

}