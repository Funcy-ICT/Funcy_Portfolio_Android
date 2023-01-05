package com.example.funcy_portfolio_android.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentAuthenticationBinding
import com.example.funcy_portfolio_android.model.localDataSource.UserIdSavePref

class AuthenticationFragment: Fragment() {
    private lateinit var binding: FragmentAuthenticationBinding
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authentication, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userIdPref = UserIdSavePref(requireActivity())

        binding.buttonUserID.setOnClickListener {
            val userId = userIdPref.readPrefUserId()
            Toast.makeText(context, userId, Toast.LENGTH_LONG).show()
        }
    }
}