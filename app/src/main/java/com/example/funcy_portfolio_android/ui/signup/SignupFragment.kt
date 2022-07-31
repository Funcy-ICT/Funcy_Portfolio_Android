package com.example.funcy_portfolio_android.ui.signup

import android.app.AlertDialog
import android.content.DialogInterface
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignup.setOnClickListener {
            val grade = binding.spinnerGrade.selectedItem.toString()
            val course = binding.spinnerCourse.selectedItem.toString()
            if(!setError()){
                AlertDialog.Builder(activity)
                    .setTitle("この内容で送信しますか？")
                    .setPositiveButton("登録する", DialogInterface.OnClickListener { dialog, which ->
                        //post
                        viewModel.sendToServerSignupData("Token1", grade, course)
                    })
                    .setNegativeButton("キャンセル", null)
                    .show()
            }else{
                Toast.makeText(context, "入力エラー", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            viewModel.setCourseId()
        })
        viewModel.courseId.observe(viewLifecycleOwner, Observer {
            val courses = resources.getStringArray(it)
            viewModel.setCourseSource(courses)
        })

        viewModel.signupStatus.observe(viewLifecycleOwner, Observer { status ->
            when(status){
                SignupApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.buttonSignup.visibility = View.GONE
                }
                SignupApiStatus.DONE -> {
                    binding.progressBar.visibility = View.GONE
                    binding.buttonSignup.visibility = View.VISIBLE
                    findNavController().navigate(R.id.action_SignupFragment_to_MainFragment)
                }
                SignupApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.buttonSignup.visibility = View.VISIBLE
                    Toast.makeText(context,"通信エラー", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setError(): Boolean{
        var errors = false

        //空のチェック
        if(binding.editDisplayName.text.isNullOrEmpty()){
            binding.inputDisplayName.isErrorEnabled = true
            binding.inputDisplayName.error = "表示名を入力してください"
            errors = true
        }else{
            binding.inputDisplayName.isErrorEnabled = false
        }

        if(binding.editFamilyName.text.isNullOrEmpty()){
            binding.inputFamilyName.isErrorEnabled = true
            binding.inputFamilyName.error = "姓を入力してください"
            errors = true
        }else {
            binding.inputFamilyName.isErrorEnabled = false
        }

        if(binding.editFirstName.text.isNullOrEmpty()){
            binding.inputFirstName.isErrorEnabled = true
            binding.inputFirstName.error = "名を入力してください"
            errors = true
        }else {
            binding.inputFirstName.isErrorEnabled = false
        }

        if(binding.editPassword.text.isNullOrEmpty()){
            binding.inputPassword.isErrorEnabled = true
            binding.inputPassword.error = "パスワードを入力してください"
            errors = true
        }else {
            binding.inputPassword.isErrorEnabled = false
        }

        if(binding.editMailAddress.text.isNullOrEmpty()){
            binding.inputMailAddress.isErrorEnabled = true
            binding.inputMailAddress.error = "学籍番号を入力してください"
            errors = true
        }else {
            binding.inputMailAddress.isErrorEnabled = false
        }

        //パスワード比較
        if(viewModel.comparePassword()){
            binding.inputConfirmPassword.isErrorEnabled = true
            binding.inputConfirmPassword.error = "パスワードが一致しません"
            errors = true
        } else {
            binding.inputConfirmPassword.isErrorEnabled = false
        }

        //学籍正規表現チェック
        if(viewModel.checkMail()){
            binding.inputMailAddress.isErrorEnabled = true
            binding.inputMailAddress.error = "入力した値が間違っています"
            errors = true
        }else {
            binding.inputMailAddress.isErrorEnabled = false
        }

        return errors
    }
}