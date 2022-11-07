package com.example.funcy_portfolio_android.ui.groupRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupBinding
import com.example.funcy_portfolio_android.databinding.FragmentGroupRegisterBinding

class GroupRegisterFragment : Fragment() {
    private lateinit var binding : FragmentGroupRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btRegisterGroupCreation.setOnClickListener {
            findNavController().navigate(R.id.action_GroupRegisterFragment_to_groupCreationRegisterFragment)
        }
        binding.btGroupMypage.setOnClickListener {
            findNavController().navigate(R.id.action_GroupRegisterFragment_to_groupMypageFragment)
        }
    }
}