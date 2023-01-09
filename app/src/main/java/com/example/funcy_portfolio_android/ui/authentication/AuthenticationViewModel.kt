package com.example.funcy_portfolio_android.ui.authentication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.AuthData
import com.example.funcy_portfolio_android.model.apiService
import kotlinx.coroutines.launch

class AuthenticationViewModel: ViewModel() {
    val inputCode = MutableLiveData<String>()

    fun sendAuthCode(userId: String){
        viewModelScope.launch {
            try {
                apiService.service.sendAuthCode(
                    AuthData(inputCode.value!!, userId)
                )
                Log.d("Authentication", "ok")
            }catch (e: Exception){
                Log.d("Authentication", "エラー$e")
            }
        }
    }
}