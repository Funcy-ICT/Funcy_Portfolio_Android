package com.example.funcy_portfolio_android.ui.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupBinding

class GroupFragment : Fragment() {

    private lateinit var binding: FragmentGroupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_GroupFragment_to_GroupRegisterFragment)
        }

        binding.button4.setOnClickListener {
            findNavController().navigate(R.id.action_GroupFragment_to_GroupListFragment)
        }
    }
}