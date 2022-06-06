package com.example.funcy_portfolio_android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

/*FUNCYサーバーとの接続を確認するステータスを定義*/
enum class FuncyApiStatus {LOADING, ERROR, DONE}

class MainViewModel : ViewModel(){

    //作品一覧を収納するデータホルダーを定義
    private val _articles = MutableLiveData<List<ArticleData>>()
    val articles: LiveData<List<ArticleData>> = _articles

    //Funcyサーバーとの接続状況を収納するホルダーを定義
    private val _status = MutableLiveData<FuncyApiStatus>()
    val status: LiveData<FuncyApiStatus> = _status

    init{
        getArticles()
    }

    private fun getArticles(){
        viewModelScope.launch {
            _status.value = FuncyApiStatus.LOADING
            try{
                _articles.value = FuncyApi.retrofitService.getArticles()
                _status.value = FuncyApiStatus.DONE
            } catch (e: Exception){
                _status.value = FuncyApiStatus.ERROR
                _articles.value = listOf()
            }

        }
    }

}