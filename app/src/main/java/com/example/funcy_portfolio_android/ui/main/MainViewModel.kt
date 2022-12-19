package com.example.funcy_portfolio_android.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.WorkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

/*FUNCYサーバーとの接続を確認するステータスを定義*/
enum class FuncyApiStatus {LOADING, ERROR, DONE}

class MainViewModel : ViewModel(){

    //作品一覧を収納するデータホルダーを定義
    private val _works = MutableLiveData<List<WorkData>>()
    val works: LiveData<List<WorkData>> = _works

    //検索用タグを収納するデータホルダーを定義
    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>> = _tags

    //Funcyサーバーとの接続状況を収納するホルダーを定義
    private val _status = MutableLiveData<FuncyApiStatus>()
    val status: LiveData<FuncyApiStatus> = _status

    //検索用ワードを収納するデータホルダーを定義
    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    //検索アラート用
    private val _searchFlag = MutableLiveData<Boolean>()
    val searchFlag: LiveData<Boolean> = _searchFlag

    init{
        getWorks("Token1")
        getTags("Token1")
    }

    private fun getWorks(token: String){
        viewModelScope.launch {
            _status.value = FuncyApiStatus.LOADING
            try{
                _works.value = FuncyApi.retrofitService.getWorks(token)
                _status.value = FuncyApiStatus.DONE
                Log.d(TAG, "通信出来たよ")
            } catch (e: Exception){
                _status.value = FuncyApiStatus.ERROR
                _works.value = listOf()
                Log.e(TAG, e.message.toString())
            }
        }
    }


    private fun getTags(token: String){
        /*ToDo: 仮置きデータ*/
        _tags.value = listOf("C", "Java", "COBOL", "Swift", "Kotlin", "bar", "foo","hoge", "piyo")
    }


    // 虫眼鏡タップ時BindingAdapter内でコールされる
    fun search(word: String, page: Int) {
        _query.value = word
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                /*ToDo: モックでき次第いい感じにする*/
//                _works.value = apiService.service.getSearchResult(_query.value!!)
                _searchFlag.postValue(true)
            }
            if(_works.value == null || _works.value!!.isEmpty()) {
                _searchFlag.postValue(false)
            }
        }

    }

    companion object {
        const val TAG = "Main"
    }
}