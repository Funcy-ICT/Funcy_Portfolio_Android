package com.example.funcy_portfolio_android.ui.creationRegister

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.CreationData
import com.example.funcy_portfolio_android.model.ImageData
import com.example.funcy_portfolio_android.model.Repository
import com.example.funcy_portfolio_android.model.TagData
import kotlinx.coroutines.launch

class CreationRegisterViewModel() : ViewModel() {

    private val repository = Repository()

    private val _tagList: MutableLiveData<List<TagData>> = MutableLiveData(listOf())
    val tagList: LiveData<List<TagData>> = _tagList

    val tags = mutableListOf<TagData>()
    var res: String? = ""

    fun getTag(): List<TagData> {
        return tags.toList()
    }

    fun setTag(tag: String) {
        tags.add(TagData(tag))
        _tagList.value = tags
    }

    fun resetTag(): List<TagData>{
        tags.clear()
        return tags.toList()
    }

    fun registerCreation(
        title: String,
        description: String,
        security: Int,
        work_url: String,
        youtube_url: String
    ): String? {
        viewModelScope.launch{
            res = repository.registerCreation(CreationData( title,description, listOf(ImageData("")), work_url, youtube_url, tags,null,security))
            Log.e("res",res!!)
        }

        return res
    }
}