package com.example.funcy_portfolio_android.ui.signup

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentSignupBinding
import com.example.funcy_portfolio_android.model.localDataSource.Keys
import com.example.funcy_portfolio_android.model.localDataSource.SharedPref


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

            val graduateKey = mapOf(
                "学部1年" to "b1",
                "学部2年" to "b2",
                "学部3年" to "b3",
                "学部4年" to "b4",
                "修士1年" to "m1",
                "修士2年" to "m2",
                "博士1年" to "d1",
                "博士2年" to "d2",
                "博士3年" to "d3",
                "教員" to "teacher"
            )

            val coursesKey = mapOf(
                "コース割当なし" to "no_course",
                "情報システムコース" to "information_systems_course",
                "高度ICTコース" to "advanced_ict_course",
                "情報デザインコース" to "information_design_course",
                "複雑系コース" to "complex_systems_course",
                "知能システムコース" to "intelligent_systems_course",
                "情報アーキテクチャ領域" to "media_architecture_field",
                "高度ICT領域" to "advanced_ict_field",
                "メディアデザイン領域" to "media_design_field",
                "複雑系情報科学領域" to "complex_systems_information_science_field",
                "知能情報科学領域" to "intelligent_information_science_field",
                "教員" to "teacher"
            )

            var gradeKey = graduateKey.getValue(grade)
            var courseKey = coursesKey.getValue(course)

            if (!setError()) {
                AlertDialog.Builder(activity)
                    .setTitle("この内容で送信しますか？")
                    .setPositiveButton("登録する", DialogInterface.OnClickListener { dialog, which ->
                        //post
                        viewModel.sendToServerSignupData("Token1", gradeKey, courseKey)
                    })
                    .setNegativeButton("キャンセル", null)
                    .show()
            } else {
                Toast.makeText(context, "入力エラー", Toast.LENGTH_SHORT).show()
            }

            //キーボードの格納
            val inputManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            viewModel.setCourseId()
        })
        viewModel.courseId.observe(viewLifecycleOwner, Observer {
            val courses = resources.getStringArray(it)
            viewModel.setCourseSource(courses)
        })

        viewModel.signupStatus.observe(viewLifecycleOwner, Observer { status ->
            val sharedPref = SharedPref(requireActivity())
            when (status) {
                SignupApiStatus.LOADING -> {
                    binding.imageDone.visibility = View.GONE
                    binding.background.visibility = View.VISIBLE
                    binding.progressDialog.visibility = View.VISIBLE
                    binding.buttonSignup.visibility = View.GONE
                }

                SignupApiStatus.SUCCESS -> {
                    binding.textDialog.text =
                        resources.getString(R.string.comp_registration_message)
                    binding.progressBar.visibility = View.GONE
                    binding.imageDone.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            binding.background.visibility = View.GONE
                            binding.progressDialog.visibility = View.GONE
                            binding.buttonSignup.visibility = View.VISIBLE
                            sharedPref.saveSharedPrefString(
                                Keys.USERID.name,
                                viewModel.userId.value.toString()
                            )
                            findNavController().navigate(R.id.action_SignupFragment_to_authenticationFragment)
                        }, 2000
                    )
                }

                SignupApiStatus.FAILURE -> {
                    binding.background.visibility = View.GONE
                    binding.progressDialog.visibility = View.GONE
                    binding.buttonSignup.visibility = View.VISIBLE
                    Toast.makeText(context, "エラー", Toast.LENGTH_SHORT).show()
                }

                SignupApiStatus.INIT -> {}
                else -> {}
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

        //これを発動させるためにxmlの背景部分にあたるView(constraintlayoutなど)の
        //focusableとfocusableInTouchをtrueにセットする必要あり
        //↓もしフォーカスが外れたらキーボードを閉じる
        binding.editDisplayName.setOnFocusChangeListener{ _,hasFocus->  //x
            if(!hasFocus)showoffKeyboard()
        }
        binding.editDisplayName.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.editDisplayName.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.editDisplayName.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.editDisplayName.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.editConfirmPassword.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }

        //キー入力を検知してフォーカスを外す
        binding.editDisplayName.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.editDisplayName.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.editFirstName.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.editMailAddress.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.editPassword.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.editConfirmPassword.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }

    }

    private fun getActions(i:Int,keyEvent:KeyEvent?):Boolean{
        if( i== EditorInfo.IME_ACTION_SEARCH||
            i== EditorInfo.IME_ACTION_DONE||
            keyEvent!=null&&
            keyEvent.action == KeyEvent.ACTION_DOWN&&
            keyEvent.keyCode== KeyEvent.KEYCODE_ENTER){
            if(keyEvent==null||!keyEvent.isShiftPressed){
                binding.root.requestFocus()
                return true
            }else{
                return false
            }
        }else{
            return false
        }
    }

    private fun showoffKeyboard(){
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
