package com.example.funcy_portfolio_android.ui.workRegister

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.data.ImageData
import com.example.funcy_portfolio_android.model.data.TagData
import com.example.funcy_portfolio_android.model.data.WorkData
import com.example.funcy_portfolio_android.model.repository.WorkRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WorkRegisterViewModel : ViewModel() {

    private val workRepository = WorkRepository()

    private val _tagList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val tagList: LiveData<List<String>> = _tagList
    val tags = mutableListOf<String>()

    private var _thumbnail = MutableLiveData<MutableList<Uri>>()
    val thumbnail: LiveData<MutableList<Uri>>
        get() = _thumbnail
    private var tagFlag: Int

    enum class WorkRegisterApiStatus { LOADING, FAILURE, SUCCESS, INIT }

    private val _workRegisterStatus =
        MutableLiveData<WorkRegisterViewModel.WorkRegisterApiStatus>(WorkRegisterViewModel.WorkRegisterApiStatus.INIT)
    val workRegisterStatus: LiveData<WorkRegisterApiStatus> = _workRegisterStatus

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
        _workRegisterStatus.value = WorkRegisterViewModel.WorkRegisterApiStatus.LOADING
        var res: String? = ""
        viewModelScope.launch {
            try {
                val postTagList = stringTagListToTagList(tags)
                res = workRepository.registerWork(
                    WorkData(
                        title,
                        description,
                        listOf(ImageData("")),
                        work_url,
                        youtube_url,
                        postTagList,
                        null,
                        security
                    )
                )
                if (res != "") {
                    Log.d("WorkRegister", "送信成功: ${res!!}")
                    _workRegisterStatus.value = WorkRegisterViewModel.WorkRegisterApiStatus.SUCCESS
                } else {
                    Log.d("WorkRegister", "エラー: ${res!!}")
                    _workRegisterStatus.value = WorkRegisterViewModel.WorkRegisterApiStatus.FAILURE
                }
            } catch (e: HttpException) {
                Log.d("WorkRegister", "通信エラー$e")
                _workRegisterStatus.value = WorkRegisterViewModel.WorkRegisterApiStatus.FAILURE
            } catch (e: Throwable) {
                Log.d("WorkRegister", "エラー$e")
                _workRegisterStatus.value = WorkRegisterViewModel.WorkRegisterApiStatus.FAILURE
            }
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