package com.example.funcy_portfolio_android.ui.creationDetail

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
import com.example.funcy_portfolio_android.ui.creationDetail.network.Creation
import com.example.funcy_portfolio_android.ui.creationDetail.network.CreationDetailNetwork
import com.example.funcy_portfolio_android.ui.creationDetail.network.Image
import com.example.funcy_portfolio_android.ui.creationDetail.network.Tag
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch

enum class CreationApiStatus{LOADING, ERROR, DONE}

private const val TAG = "CreationDetailViewModel"

class CreationDetailViewModel: ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    //ここから作品詳細
    private val _creation = MutableLiveData<Creation>()
    val creation: LiveData<Creation> = _creation

    private val _creationDetailStatus = MutableLiveData<CreationApiStatus>()
    val creationDetailStatus: LiveData<CreationApiStatus> = _creationDetailStatus

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    private val _explanation = MutableLiveData<String>()
    val explanation: LiveData<String> = _explanation

    private val _tags = MutableLiveData<List<Tag>>()
    val tags: LiveData<List<Tag>> = _tags

    private val _youtubeUrl = MutableLiveData<String>()
    val youtubeUrl: LiveData<String> = _youtubeUrl

    private val _githubUrl = MutableLiveData<String>()
    val githubUrl: LiveData<String> = _githubUrl

    init{
        //仮置きのテキスト達
        _userName.value = "田中太郎"
        getCreationFromNetwork("w1")
    }


    private fun getCreationFromNetwork(creationId: String) {
        viewModelScope.launch {
            _creationDetailStatus.value = CreationApiStatus.LOADING
            try {
                _creation.value = CreationDetailNetwork.creationDetail.getCreationDetail(creationId)
                Log.e(TAG, "アクセス成功")
                _creationDetailStatus.value = CreationApiStatus.DONE
            }catch (e: Exception){
                Log.e(TAG, "アクセス失敗　エラー：$e")
                _creationDetailStatus.value = CreationApiStatus.ERROR
            }
        }
    }

    fun setEachTag(chipGroup: ChipGroup, context: Context){
        chipGroup.removeAllViews()
        _tags.value?.forEach { tag ->
            val chip = Chip(context)
            chip.text = tag.Tag
            chipGroup.addView(chip)
        }
    }

    fun setCreationDetail(){
        val creationValue = _creation.value!!
        _title.value = creationValue.title
        _images.value = creationValue.images
        _explanation.value = creationValue.description
        _tags.value = creationValue.tags
        _youtubeUrl.value = creationValue.movie_url
        _githubUrl.value = creationValue.URL
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