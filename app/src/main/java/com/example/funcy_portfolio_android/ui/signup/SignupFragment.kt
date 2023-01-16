package com.example.funcy_portfolio_android.ui.signup

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
            findNavController().navigate(R.id.action_SignupFragment_to_MainFragment)
        }

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            viewModel.setCourseId()
        })
        viewModel.courseId.observe(viewLifecycleOwner, Observer {
            val courses = resources.getStringArray(it)
            viewModel.setCourseSource(courses)
        })

        //これを発動させるためにxmlの背景部分にあたるView(constraintlayoutなど)の
        //focusableとfocusableInTouchをtrueにセットする必要あり
        //↓もしフォーカスが外れたらキーボードを閉じる
        binding.etPublicName.setOnFocusChangeListener{ _,hasFocus->  //x
            if(!hasFocus)showoffKeyboard()
        }
        binding.etFamilyName.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etFirstName.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etMailAddress.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etPassword.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etConfirmPassword.setOnFocusChangeListener{ _,hasFocus->
            if(!hasFocus)showoffKeyboard()
        }

        //キー入力を検知してフォーカスを外す
        binding.etPublicName.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etFamilyName.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etFirstName.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etMailAddress.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etPassword.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etConfirmPassword.setOnEditorActionListener { _, i, keyEvent ->
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