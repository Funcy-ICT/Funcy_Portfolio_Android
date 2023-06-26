package com.example.funcy_portfolio_android.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentLoginBinding
import com.example.funcy_portfolio_android.ui.common.KeyboardUtils


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
            if (hasFocus) KeyboardUtils.showoffKeyboard(requireContext(), binding.root.windowToken)
        }

        binding.btLogin.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_MainFragment)
        }
        binding.btUserRegister.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SignupFragment)
        }
    }
}
