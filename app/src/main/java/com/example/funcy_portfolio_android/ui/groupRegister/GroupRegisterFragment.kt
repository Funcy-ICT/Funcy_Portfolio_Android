package com.example.funcy_portfolio_android.ui.groupRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupRegisterBinding
import com.example.funcy_portfolio_android.ui.common.KeyboardUtils

class GroupRegisterFragment : Fragment() {
    private lateinit var binding: FragmentGroupRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btRegisterGroupWork.setOnClickListener {
            findNavController().navigate(R.id.action_GroupRegisterFragment_to_groupWorkRegisterFragment)
        }
        binding.btGroupMypage.setOnClickListener {
            findNavController().navigate(R.id.action_GroupRegisterFragment_to_groupMypageFragment)
        }

        //他を押したときにキーボード閉じる処理（対象のviewにfocusableとfocusableInTouchModeをtrueでつける必要あり）
        binding.layout.setOnFocusChangeListener { _, hasFocus ->  //x
            if (hasFocus) KeyboardUtils.showoffKeyboard(requireContext(), binding.root.windowToken)
        }
    }
}