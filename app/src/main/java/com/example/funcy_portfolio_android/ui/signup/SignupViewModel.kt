package com.example.funcy_portfolio_android.ui.signup

import android.app.Application
import android.util.Log
import android.widget.AdapterView
import androidx.databinding.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.model.SignupData
import com.example.funcy_portfolio_android.model.apiService
import kotlinx.coroutines.launch


class SignupViewModel: ViewModel() {
    val selectedItem = MutableLiveData<Int>()

    private var _courseId = MutableLiveData<Int>()
    val courseId: LiveData<Int> = _courseId

    private var _courseResource = MutableLiveData<Array<String>>()
    val courseResource: LiveData<Array<String>> = _courseResource

    val displayName = MutableLiveData<String>()
    val familyName = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val mailAddress = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    private val checkMailPattern = Regex("^(b|g)([0-9]{7})")

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
        viewModelScope.launch {
            try {
                apiService.service.sendUserRegistration(
                    token,
                    SignupData(familyName.value!!, course, displayName.value!!, firstName.value!!, grade, "noIcon",sendMailAddress,password.value!!)
                )
                Log.d("SignUp", "送信成功")
            }catch (e: Exception){
                Log.d("SignUp", "エラー$e")
            }

        }
    }

    fun comparePassword(): Boolean{
        if(password.value != confirmPassword.value){
            return true
        }
        return false
    }

    fun checkMail():Boolean{
        return !checkMailPattern.matches(mailAddress.value.toString())
    }
}
