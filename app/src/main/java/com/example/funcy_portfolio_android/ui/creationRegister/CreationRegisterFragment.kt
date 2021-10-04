package com.example.funcy_portfolio_android.ui.creationRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBinding
import com.example.funcy_portfolio_android.databinding.FragmentMainBinding

class CreationRegisterFragment : Fragment() {

    private lateinit var binding: FragmentCreationRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreationRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_CreationRegisterFragment_to_MainFragment)
        }

    }

}