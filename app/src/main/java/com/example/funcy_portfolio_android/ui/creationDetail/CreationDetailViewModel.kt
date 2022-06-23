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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch
import java.io.IOException

enum class CreationApiStatus{LOADING, ERROR, DONE}

class CreationDetailViewModel: ViewModel() {
    private val _creation = MutableLiveData<Creation>()
    val creation: LiveData<Creation> = _creation

    private val _creationDetailStatus = MutableLiveData<CreationApiStatus>()
    val creationDetailStatus: LiveData<CreationApiStatus> = _creationDetailStatus

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _explanation = MutableLiveData<String>()
    val explanation: LiveData<String> = _explanation

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>> = _tags

    private val _youtubeUrl = MutableLiveData<String>()
    val youtubeUrl: LiveData<String> = _youtubeUrl

    private val _githubUrl = MutableLiveData<String>()
    val githubUrl: LiveData<String> = _githubUrl

    init{
        //仮置きのテキスト達
        _userName.value = "田中太郎"
        _title.value = "ブロック崩し"
        _explanation.value = "授業で作ったよ\nよくあるブロック崩しだよ\nほげほげ\nほーげほげ"
        _tags.value = listOf("processing", "ブロック崩し", "情報処理演習","ほげほげ","ぴよぴよ")
        _youtubeUrl.value = "https://www.youtube.com/watch?v=IoIl_ZE_YPM"
        _githubUrl.value = "https://github.com/Funcy-ICT"
        getCreationFromNetwork("Token1", "01G5YDNC8ZQQNJZ149SWS67VD1")
    }


    private fun getCreationFromNetwork(token: String, creationId: String) = viewModelScope.launch {
        _creationDetailStatus.value = CreationApiStatus.LOADING
        try {
            _creation.value = CreationDetailNetwork.creationDetail.getCreationDetail(token, creationId)
            _creationDetailStatus.value = CreationApiStatus.DONE
        }catch (networkError: IOException){
            Log.e("CreationDetail", "ネットワークエラー")
            _creationDetailStatus.value = CreationApiStatus.ERROR
        }
    }

    fun setEachTag(chipGroup: ChipGroup, context: Context){
        chipGroup.removeAllViews()
        _tags.value?.forEach { tag ->
            val chip = Chip(context)
            chip.text = tag
            chipGroup.addView(chip)
        }
    }

    fun setCreationDetail(){
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