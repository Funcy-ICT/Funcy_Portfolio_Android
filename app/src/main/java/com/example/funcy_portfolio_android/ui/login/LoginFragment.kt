package com.example.funcy_portfolio_android.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.clLogin.setOnFocusChangeListener { _, hasFocus ->  //x
            if (hasFocus) showoffKeyboard()
        }
        binding.etPassword.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                showoffKeyboard()
            }
        }

        binding.btLogin.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_MainFragment)
        }
        binding.btUserRegister.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SignupFragment)
        }
    }

    fun showoffKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
