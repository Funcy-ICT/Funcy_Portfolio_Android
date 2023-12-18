package com.example.funcy_portfolio_android.ui.workRegister

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.data.ImageData
import com.example.funcy_portfolio_android.model.data.TagData
import com.example.funcy_portfolio_android.model.data.WorkDetails
import com.example.funcy_portfolio_android.model.repository.WorkRepository
import kotlinx.coroutines.launch

class WorkRegisterViewModel : ViewModel() {

    private val workRepository = WorkRepository()

    var res: String? = ""

    private val _tagList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val tagList: LiveData<List<String>> = _tagList
    val tags = mutableListOf<String>()

    private var _thumbnail = MutableLiveData<MutableList<Uri>>()
    val thumbnail: LiveData<MutableList<Uri>>
        get() = _thumbnail
    private var tagFlag: Int

    init {
        //サムネイルの初期値として[no_image]の画像を設定
        _thumbnail.value =
            mutableListOf<Uri>(Uri.parse("android.resource://com.example.funcy_portfolio_android/drawable/img_no_image"))
        tagFlag = -1
    }

    fun getTag(): List<String> {
        return tags.toList()
    }

    fun setTag(tag: String): Boolean {
        return if (!tags.contains(tag)) {
            tags.add(tag)
            _tagList.value = tags
            true
        } else {
            false
        }
    }

    fun resetTag(): List<String> {
        tags.clear()
        return tags.toList()
    }

    fun removeTag(tag: String) {
        tags -= tag
        _tagList.value = tags
    }

    fun saveImage(thumbnails: List<Uri>) {
        _thumbnail.value?.clear()
        _thumbnail.value = thumbnails.toMutableList()
    }

    fun addTagFlag() {
        tagFlag = 1
    }

    fun delTagFlag() {
        tagFlag = -1
    }

    fun getTagFlag(): Boolean {
        return (tagFlag > 0)
    }

    fun registerWork(
        title: String,
        description: String,
        security: Int,
        work_url: String,
        youtube_url: String
    ): String? {
        viewModelScope.launch {
            val postTagList = stringTagListToTagList(tags)
            res = workRepository.registerWork(
                WorkDetails(
                    title = title,
                    description = description,
                    thumbnail = "",
                    user_icon = "",
                    user_name = "",
                    userID = "",
                    images = listOf(ImageData("")),
                    work_url = work_url,
                    movie_url = youtube_url,
                    tags = postTagList,
                    group = null,
                    security = security
                )
            )
            Log.e("res", res!!)
        }

        return res
    }

    //tagsのList<String>をList<TagData>に変換する(一応プライベートにしました)
    private fun stringTagListToTagList(stringList: List<String>): List<TagData> {
        val tagsList = mutableListOf<TagData>()
        stringList.forEach {
            tagsList.add(TagData(it))
        }
        return tagsList
    }
}