package com.example.funcy_portfolio_android.ui.creationDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreationDetailViewModel: ViewModel() {
    private val _userName = MutableLiveData<String>("")
    val userName: LiveData<String> = _userName

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _explanation = MutableLiveData<String>()
    val explanation: LiveData<String> = _explanation

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>> = _tags

    init{
        //仮置きのテキスト達
        _userName.value = "田中太郎"
        _title.value = "ブロック崩し"
        _explanation.value = "授業で作ったよ\nよくあるブロック崩しだよ\nほげほげ\nほーげほげ"
        _tags.value = listOf("processing", "ブロック崩し", "情報処理演習","ほげほげ","ぴよぴよ")
    }

}