package com.example.funcy_portfolio_android.ui.login

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //他を押したときにキーボード閉じる処理（対象のviewにfocusableとfocusableInTouchModeをtrueでつける必要あり）
        binding.etMailAddress.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                showoffKeyboard()
            }
        }
        binding.etPassword.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                showoffKeyboard()
            }
        }

        //パソコンのエンターキー押したときにキーボードを閉じる処理（スマホキーボードのエンターキー？）
        binding.etMailAddress.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i, keyEvent)
        }
        binding.etPassword.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i, keyEvent)
        }


        binding.btLogin.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_MainFragment)
        }
        binding.btUserRegister.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SignupFragment)
        }
    }

    fun getActions(i: Int, keyEvent: KeyEvent?): Boolean {
        if (i == EditorInfo.IME_ACTION_SEARCH ||
            i == EditorInfo.IME_ACTION_DONE ||
            keyEvent != null &&
            keyEvent.action == KeyEvent.ACTION_DOWN &&
            keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
        ) {
            if (keyEvent == null || !keyEvent.isShiftPressed) {
                binding.root.requestFocus()
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }

    fun showoffKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
