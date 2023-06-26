package com.example.funcy_portfolio_android.ui.workDetail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funcy_portfolio_android.model.data.ImageData
import com.example.funcy_portfolio_android.model.data.TagData
import com.example.funcy_portfolio_android.model.data.WorkData
import com.example.funcy_portfolio_android.model.repository.WorkRepository
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch

class WorkDetailViewModel : ViewModel() {
    private val TAG = "WorkDetailViewModel"

    private val workRepository = WorkRepository()

    enum class WorkApiStatus { LOADING, ERROR, DONE }

    private val _userName = MutableLiveData<String>("田中太郎")
    val userName: LiveData<String> = _userName

    //ここから作品詳細
    private val _work = MutableLiveData<WorkData>()
    val work: LiveData<WorkData> = _work

    private val _workDetailStatus = MutableLiveData<WorkApiStatus>()
    val workDetailStatus: LiveData<WorkApiStatus> = _workDetailStatus

    private val _title = MutableLiveData<String>("こんにちはアプリ")
    val title: LiveData<String> = _title

    private val _images = MutableLiveData<List<ImageData>>()
    val images: LiveData<List<ImageData>> = _images

    private val _explanation = MutableLiveData<String>("あああああああああああああ")
    val explanation: LiveData<String> = _explanation

    private val _tagList = MutableLiveData<List<TagData>>()
    val tagList: LiveData<List<TagData>> = _tagList

    private val _youtubeUrl = MutableLiveData<String>()
    val youtubeUrl: LiveData<String> = _youtubeUrl

    private val _githubUrl = MutableLiveData<String>()
    val githubUrl: LiveData<String> = _githubUrl

    init {
        getWorkFromNetwork("Token1", "w1")
    }


    private fun getWorkFromNetwork(token: String, workId: String) {
        viewModelScope.launch {
            _workDetailStatus.value = WorkApiStatus.LOADING
            try {
                _work.value = workRepository.getWorkDetail(token, workId)
                Log.e(TAG, "アクセス成功")
                _workDetailStatus.value = WorkApiStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG, "アクセス失敗　エラー：$e")
                _workDetailStatus.value = WorkApiStatus.ERROR
            }
        }
    }

    fun setEachTag(chipGroup: ChipGroup, context: Context) {
        chipGroup.removeAllViews()
        _tagList.value?.forEach { tag ->
            val chip = Chip(context)
            chip.text = tag.Tag
            chipGroup.addView(chip)
        }
    }

    fun setWorkDetail() {
        val workValue = _work.value!!
        _title.value = workValue.title
        _images.value = workValue.images
        _explanation.value = workValue.description
        _tagList.value = workValue.tags
        _youtubeUrl.value = workValue.movie_url
        _githubUrl.value = workValue.URL
    }

    //Web遷移系の処理//////////////////////////////////
    /** YouTube */
    fun navigateToYouTube(url: String, context: Context) {
        val uri = Uri.parse(url)
        try {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            navigateToCustomTab(url, context)
        }
    }

    /** GitHub用 CustomTab */
    fun navigateToCustomTab(url: String, context: Context) {
        val uri = Uri.parse(url)
        CustomTabsIntent.Builder().also { builder ->
            builder.setShowTitle(true)
            builder.build().also {
                it.launchUrl(context, uri)
            }
        }
    }
}