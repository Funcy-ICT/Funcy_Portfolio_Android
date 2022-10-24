package com.example.funcy_portfolio_android.ui.signup

import android.app.Application
import android.util.Log
import android.widget.AdapterView
import androidx.databinding.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.funcy_portfolio_android.R


class SignupViewModel: ViewModel() {
    val selectedItem = MutableLiveData<Int>()

    private var _courseId = MutableLiveData<Int>()
    val courseId: LiveData<Int> = _courseId

    private var _courseResource = MutableLiveData<Array<String>>()
    val courseResource: LiveData<Array<String>> = _courseResource

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
}
