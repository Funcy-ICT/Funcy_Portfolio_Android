package com.example.funcy_portfolio_android.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val searchData: MutableLiveData<String> = MutableLiveData()

    // 虫眼鏡タップ時BindingAdapter内でコールされる
    fun search(query: String) {
        // バリデートされた文字列をMainActivityにPostする
        searchData.postValue(query)
    }
}