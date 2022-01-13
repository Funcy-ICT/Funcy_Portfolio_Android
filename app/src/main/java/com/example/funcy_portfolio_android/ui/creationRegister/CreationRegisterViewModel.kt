package com.example.funcy_portfolio_android.ui.creationRegister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreationRegisterViewModel() : ViewModel() {

    private val _tagList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val tagList: LiveData<List<String>> = _tagList

    val tags = mutableListOf<String>()

    fun getTag(): List<String> {
        return tags.toList()
    }

    fun setTag(tag: String){
        tags.add(tag)
        _tagList.value = tags
    }

    fun resetTag(): List<String>{
        tags.clear()
        return tags.toList()
    }



}