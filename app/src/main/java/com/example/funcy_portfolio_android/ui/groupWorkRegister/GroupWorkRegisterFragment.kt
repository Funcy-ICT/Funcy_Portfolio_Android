package com.example.funcy_portfolio_android.ui.groupWorkRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupWorkRegisterBinding
import com.example.funcy_portfolio_android.ui.common.KeyboardUtils.showoffKeyboard

class GroupWorkRegisterFragment : Fragment() {

    private lateinit var binding: FragmentGroupWorkRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_group_work_register,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //EditTextのキーボードの非表示処理
        binding.layout.setOnFocusChangeListener { _, hasFocus ->  //x
            if (hasFocus) showoffKeyboard(requireContext(), binding.root.windowToken)
        }
        binding.etGroupYoutubeLink.setOnFocusChangeListener { _, hasFocus ->  //x
            if (!hasFocus) showoffKeyboard(requireContext(), binding.root.windowToken)
        }
    }
}