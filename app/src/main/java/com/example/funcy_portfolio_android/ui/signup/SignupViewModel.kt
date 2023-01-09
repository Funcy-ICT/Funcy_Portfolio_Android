package com.example.funcy_portfolio_android.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.model.SignupData
import com.example.funcy_portfolio_android.model.apiService
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch


enum class SignupApiStatus{ LOADING, ERROR, DONE, INIT }

class SignupViewModel: ViewModel() {
    val selectedItem = MutableLiveData<Int>()

    private var _courseId = MutableLiveData<Int>()
    val courseId: LiveData<Int> = _courseId

    private var _courseResource = MutableLiveData<Array<String>>()
    val courseResource: LiveData<Array<String>> = _courseResource

    private var _userId = MutableLiveData<String>("userId")
    val userId: LiveData<String> = _userId

    val displayName = MutableLiveData<String>()
    val familyName = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val mailAddress = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    private val checkMailPattern = Regex("^(b|g)([0-9]{7})")

    private val _signupStatus = MutableLiveData<SignupApiStatus>(SignupApiStatus.INIT)
    val signupStatus: LiveData<SignupApiStatus> = _signupStatus

    fun setCourseId(){
        Log.i("こんにちはスピナーです",selectedItem.value.toString())
        val item = selectedItem.value!!
        if(item > 3){
            _courseId.value = R.array.array_doctor_courses
        }else{
            _courseId.value = R.array.array_bachelor_courses
        }
    }

    fun setCourseSource(courses: Array<String>){
        _courseResource.value = courses
    }

    fun sendToServerSignupData(token: String, grade:String, course: String){
        val sendMailAddress = mailAddress.value + "@fun.ac.jp"
        _signupStatus.value = SignupApiStatus.LOADING
        viewModelScope.launch {
            try {
                _userId.value = apiService.service.sendUserRegistration(
                    SignupData(familyName.value!!, course, displayName.value!!, firstName.value!!, grade, "noIcon", sendMailAddress, password.value!!)
                ).body()?.userID
                Log.d("SignUp", "送信成功 : ${_userId.value}")
                _signupStatus.value = SignupApiStatus.DONE
            }catch (e: Exception){
                Log.d("SignUp", "エラー$e")
                _signupStatus.value = SignupApiStatus.ERROR
            }
        }
    }


    fun comparePassword(): Boolean{
        if(password.value != confirmPassword.value){
            return true
        }
        return false
    }

    fun checkMail(): Boolean{
        return !checkMailPattern.matches(mailAddress.value.toString())
    }

    fun errorIsNullOrEmpty(editText: String?, textLayout: TextInputLayout, errorItem: String): Boolean{
        val isError: Boolean

        if(editText.isNullOrEmpty()){
            textLayout.isErrorEnabled = true
            textLayout.error = errorItem + "を入力してください"
            isError = true
        }else{
            textLayout.isErrorEnabled = false
            isError = false
        }

        return isError
    }
}
