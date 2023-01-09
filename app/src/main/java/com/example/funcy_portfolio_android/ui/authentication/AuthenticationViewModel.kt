package com.example.funcy_portfolio_android.ui.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.AuthData
import com.example.funcy_portfolio_android.model.apiService
import kotlinx.coroutines.launch

class AuthenticationViewModel: ViewModel() {
    val inputCode = MutableLiveData<String>()

    private val _authStatus = MutableLiveData<AuthApiStatus>(AuthApiStatus.INIT)
    val authStatus: LiveData<AuthApiStatus> = _authStatus

    fun sendAuthCode(userId: String){
        viewModelScope.launch {
            val response = apiService.service.sendAuthCode(
                AuthData(inputCode.value!!, userId)
            )
            if(response.isSuccessful){
                Log.d("Authentication", "認証が完了しました")
                _authStatus.value = AuthApiStatus.SUCCESS

            }else {
                Log.d("Authentication", "認証エラー$response")
                _authStatus.value = AuthApiStatus.FAILURE
            }
        }
    }
}