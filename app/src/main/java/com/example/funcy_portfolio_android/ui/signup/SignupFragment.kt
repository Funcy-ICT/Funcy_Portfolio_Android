package com.example.funcy_portfolio_android.ui.signup

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentSignupBinding
import com.google.android.material.textfield.TextInputLayout


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
                    binding.progressDialog.visibility = View.VISIBLE
                    binding.buttonSignup.visibility = View.GONE
                }
                SignupApiStatus.DONE -> {
                    binding.progressDialog.visibility = View.GONE
                    binding.buttonSignup.visibility = View.VISIBLE
                    binding.textDialog.text = "完了"
                    findNavController().navigate(R.id.action_SignupFragment_to_MainFragment)
                }
                SignupApiStatus.ERROR -> {
                    binding.progressDialog.visibility = View.GONE
                    binding.buttonSignup.visibility = View.VISIBLE
                    Toast.makeText(context,"通信エラー", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.displayName.observe(viewLifecycleOwner, Observer {

        })

    }

    private fun errorIsNullOrEmpty(editText: String?, textLayout: TextInputLayout, errorItem: String): Boolean{
        val isError: Boolean

        if(editText.isNullOrEmpty()){
            textLayout.isErrorEnabled = true
            textLayout.error = errorItem + "を入力してください"
            isError = true
        }else{
            textLayout.isErrorEnabled = false
            isError = false
        }

        return isError
    }

    private fun setError(): Boolean{
        var errors: Boolean

        errors = (
                errorIsNullOrEmpty(viewModel.displayName.value, binding.inputDisplayName, "表示名") xor
                        errorIsNullOrEmpty(viewModel.familyName.value, binding.inputFamilyName, "姓") xor
                        errorIsNullOrEmpty(viewModel.firstName.value, binding.inputFirstName, "名") xor
                        errorIsNullOrEmpty(viewModel.password.value, binding.inputPassword, "パスワード") xor
                        errorIsNullOrEmpty(viewModel.mailAddress.value, binding.inputMailAddress, "学籍番号")
                )
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