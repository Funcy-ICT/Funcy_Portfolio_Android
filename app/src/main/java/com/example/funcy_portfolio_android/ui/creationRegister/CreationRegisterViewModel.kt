package com.example.funcy_portfolio_android.ui.creationRegister

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreationRegisterViewModel : ViewModel() {
    private var _thumbnail = MutableLiveData<MutableList<Uri>>()
    val thumbnail: LiveData<MutableList<Uri>>
        get() = _thumbnail

    fun saveImage(thumbnails: List<Uri>) {
        _thumbnail.value?.clear()
        _thumbnail.value = thumbnails.toMutableList()
        Log.i("change", _thumbnail.value.toString())
    }
}
