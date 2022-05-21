package com.example.funcy_portfolio_android.ui.mypage

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MypageViewModel:ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName : LiveData<String> = _userName

    private val _profileText = MutableLiveData<Spanned>()
    val profileText : LiveData<Spanned> = _profileText

    private val _skillList = MutableLiveData<List<String>>()
    val skillList : LiveData<List<String>> = _skillList

    init {
        val htmlText ="<html>情報システムコース（学部４年）<br><br>バナナが大好きな太郎君です．<br>どうぞフォローしてください^^ <br><br> <b>メールアドレス</b>  hoge@fun.ac.jp</html>"
        _userName.value = "テストネーム"
        _profileText.value = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
        val skills = listOf(
            "Swift",
            "Illustrator",
            "鉄棒"
        )
    }
}