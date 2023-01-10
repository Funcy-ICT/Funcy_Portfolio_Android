package com.example.funcy_portfolio_android.ui.signup

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentSignupBinding
import com.example.funcy_portfolio_android.model.localDataSource.Keys
import com.example.funcy_portfolio_android.model.localDataSource.UserIdSavePref


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

            //キーボードの格納
            val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            viewModel.setCourseId()
        })
        viewModel.courseId.observe(viewLifecycleOwner, Observer {
            val courses = resources.getStringArray(it)
            viewModel.setCourseSource(courses)
        })

        viewModel.signupStatus.observe(viewLifecycleOwner, Observer { status ->
            val userIdPref = UserIdSavePref(requireActivity())
            when(status){
                SignupApiStatus.LOADING -> {
                    binding.imageDone.visibility = View.GONE
                    binding.background.visibility = View.VISIBLE
                    binding.progressDialog.visibility = View.VISIBLE
                    binding.buttonSignup.visibility = View.GONE
                }
                SignupApiStatus.DONE -> {
                    binding.textDialog.text = resources.getString(R.string.comp_registration_message)
                    binding.progressBar.visibility = View.GONE
                    binding.imageDone.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            binding.background.visibility = View.GONE
                            binding.progressDialog.visibility = View.GONE
                            binding.buttonSignup.visibility = View.VISIBLE
                            userIdPref.savePrefUserId(Keys.USERID.name, viewModel.userId.value.toString())
                            findNavController().navigate(R.id.action_SignupFragment_to_authenticationFragment)
                        }
                        ,2000
                    )
                }
                SignupApiStatus.ERROR -> {
                    binding.background.visibility = View.GONE
                    binding.progressDialog.visibility = View.GONE
                    binding.buttonSignup.visibility = View.VISIBLE
                    Toast.makeText(context,"通信エラー", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setError(): Boolean {
        var mailError = false
        var passError = false

        //未入力エラーハンドリング，どれか一つでもエラーならエラーに
        val emptyError = (
                viewModel.errorIsNullOrEmpty(
                    viewModel.displayName.value,
                    binding.inputDisplayName,
                    "表示名"
                ) or
                        viewModel.errorIsNullOrEmpty(
                            viewModel.familyName.value,
                            binding.inputFamilyName,
                            "姓"
                        ) or
                        viewModel.errorIsNullOrEmpty(
                            viewModel.firstName.value,
                            binding.inputFirstName,
                            "名"
                        ) or
                        viewModel.errorIsNullOrEmpty(
                            viewModel.password.value,
                            binding.inputPassword,
                            "パスワード"
                        ) or
                        viewModel.errorIsNullOrEmpty(
                            viewModel.mailAddress.value,
                            binding.inputMailAddress,
                            "学籍番号"
                        )
                )

        //パスワード比較
        if (viewModel.comparePassword()) {
            binding.inputConfirmPassword.isErrorEnabled = true
            binding.inputConfirmPassword.error = "パスワードが一致しません"
            passError = true
        } else {
            binding.inputConfirmPassword.isErrorEnabled = false
        }

        //学籍正規表現チェック
        if (viewModel.checkMail()) {
            binding.inputMailAddress.isErrorEnabled = true
            binding.inputMailAddress.error = "入力した値が間違っています"
            mailError = true
        } else {
            binding.inputMailAddress.isErrorEnabled = false
        }


        Log.d("Error", (emptyError or mailError or passError).toString())
        return emptyError or mailError or passError
    }
}
