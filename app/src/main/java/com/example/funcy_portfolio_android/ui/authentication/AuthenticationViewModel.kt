package com.example.funcy_portfolio_android.ui.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.data.AuthData
import com.example.funcy_portfolio_android.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthenticationViewModel : ViewModel() {
    val inputCode = MutableLiveData<String>("")

    private val _authStatus = MutableLiveData<AuthApiStatus>(AuthApiStatus.INIT)
    val authStatus: LiveData<AuthApiStatus> = _authStatus

    val text = MutableLiveData<String>("")

    fun sendAuthCode(userId: String) {
        viewModelScope.launch {
            try {
                val response = ApiService.service.sendAuthCode(
                    AuthData(inputCode.value!!, userId)
                )
                if (response.isSuccessful) {
                    Log.i("Authentication", "認証が完了しました${response.body()}")
                    _authStatus.value = AuthApiStatus.SUCCESS

                } else {
                    Log.i("Authentication", "認証エラー${response.message()}")
                    _authStatus.value = AuthApiStatus.FAILURE
                }
            } catch (e: HttpException) {
                Log.i("Authentication", "通信エラー$e")
                _authStatus.value = AuthApiStatus.FAILURE

            } catch (e: Throwable) {
                Log.i("Authentication", "エラー$e")
                _authStatus.value = AuthApiStatus.FAILURE
            }
        }
    }
}