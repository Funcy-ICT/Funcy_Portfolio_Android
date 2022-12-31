package com.example.funcy_portfolio_android.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthenticationViewModel: ViewModel() {
    private var _inputCode = MutableLiveData<String>()
    val inputCode: LiveData<String> = _inputCode
}