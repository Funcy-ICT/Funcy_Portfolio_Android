package com.example.funcy_portfolio_android.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.funcy_portfolio_android.databinding.FragmentSettingBinding
import com.example.funcy_portfolio_android.ui.common.KeyboardUtils

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //他を押したときにキーボード閉じる処理（対象のviewにfocusableとfocusableInTouchModeをtrueでつける必要あり）
        binding.layout.setOnFocusChangeListener { _, hasFocus ->  //x
            if (hasFocus) KeyboardUtils.showoffKeyboard(requireContext(), binding.root.windowToken)
        }
    }

}