package com.example.funcy_portfolio_android.ui.creationRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBinding

class CreationRegisterFragment : Fragment() {

    private lateinit var binding: FragmentCreationRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreationRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_CreationRegisterFragment_to_MainFragment)
        }

        binding.swPublic.setOnCheckedChangeListener { buttonView, isChecked ->
            when (isChecked) {
                true -> buttonView.text = "公開"
                false -> buttonView.text = "非公開"
            }
        }

    }

}