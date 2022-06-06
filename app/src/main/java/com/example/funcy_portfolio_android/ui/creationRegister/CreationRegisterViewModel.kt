package com.example.funcy_portfolio_android.ui.creationRegister

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreationRegisterViewModel : ViewModel() {

    private val _tagList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val tagList: LiveData<List<String>> = _tagList
    val tags = mutableListOf<String>()

    private var _thumbnail = MutableLiveData<MutableList<Uri>>()
    val thumbnail: LiveData<MutableList<Uri>>
        get() = _thumbnail

    init{
        //サムネイルの初期値として[no_image]の画像を設定
        _thumbnail.value = mutableListOf<Uri>(Uri.parse("android.resource://com.example.funcy_portfolio_android/drawable/img_no_image"))
    }

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

    fun removeTag(position: Int){
        tags.removeAt(position)
        _tagList.value = tags
    }

    fun saveImage(thumbnails: List<Uri>) {
        _thumbnail.value?.clear()
        _thumbnail.value = thumbnails.toMutableList()
    }
}
