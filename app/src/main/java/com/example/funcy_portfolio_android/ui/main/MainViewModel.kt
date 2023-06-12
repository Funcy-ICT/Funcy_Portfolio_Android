package com.example.funcy_portfolio_android.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.WorkData
import com.example.funcy_portfolio_android.ui.creationRegister.CreationRegisterBottomSheet.Companion.TAG
import kotlinx.coroutines.launch
import java.lang.Exception

/*FUNCYサーバーとの接続を確認するステータスを定義*/
enum class FuncyApiStatus {LOADING, ERROR, DONE}

class MainViewModel : ViewModel(){

    //作品一覧を収納するデータホルダーを定義
    private val _works = MutableLiveData<List<WorkData>>()
    val works: LiveData<List<WorkData>> = _works

    //Funcyサーバーとの接続状況を収納するホルダーを定義
    private val _status = MutableLiveData<FuncyApiStatus>()
    val status: LiveData<FuncyApiStatus> = _status

    init{
        getWorks("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNmI4ZDgwMWQtZDE1My00MzQyLThlYzEtZDdhOTRiMGRhMzNiIiwiaWF0IjoxNjg2NTUzMzMzfQ.8qdMdDwu_irdE4xvXZOWH5U4GLrE7rWvbj0zZTiqjSc")
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

}