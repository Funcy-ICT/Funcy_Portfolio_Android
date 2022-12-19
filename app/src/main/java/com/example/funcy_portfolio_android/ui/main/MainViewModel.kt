package com.example.funcy_portfolio_android.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.WorkData
import com.example.funcy_portfolio_android.model.apiService
import com.example.funcy_portfolio_android.ui.creationRegister.CreationRegisterBottomSheet.Companion.TAG
import kotlinx.coroutines.launch
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
               // _works.value = addArray(_works.value!![0])  // 仮置き
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

    val searchData: MutableLiveData<String> = MutableLiveData()
    // 虫眼鏡タップ時BindingAdapter内でコールされる
    fun search(word: String) {
        // バリデートされた文字列をMainActivityにPostする
        //searchData.postValue(query)
        _query.value = word
        try{
            //_works.value = apiService.service.getSearchResult(_query.value!!)
            Log.d(TAG, "検索出来たよ")
        } catch (e: Exception){
            _status.value = FuncyApiStatus.ERROR
            Log.e(TAG, e.message.toString())
        }
    }


    /**仮配列の作成 */
    private fun addArray(work: WorkData): List<WorkData> {
        return listOf<WorkData>(work, work, work, work)
    }
}