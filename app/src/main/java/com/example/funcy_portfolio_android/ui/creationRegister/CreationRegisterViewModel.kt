package com.example.funcy_portfolio_android.ui.creationRegister

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreationRegisterViewModel : ViewModel() {
    private var _thumbnail1 = MutableLiveData<Uri>()
    val thumbnail1: LiveData<Uri>
        get() = _thumbnail1

    private var _thumbnail2 = MutableLiveData<Uri>()
    val thumbnail2: LiveData<Uri>
        get() = _thumbnail2

    private var _thumbnail3 = MutableLiveData<Uri>()
    val thumbnail3: LiveData<Uri>
        get() = _thumbnail3

    fun saveImage(thumbnails: List<Uri>) {
        _thumbnail1.value = thumbnails[0]
        _thumbnail2.value = thumbnails[1]
        _thumbnail3.value = thumbnails[2]
    }
}