package com.example.funcy_portfolio_android.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentAuthenticationBinding
import com.example.funcy_portfolio_android.model.localDataSource.UserIdSavePref

enum class AuthApiStatus{ INIT, LOADING, SUCCESS, FAILURE}

class AuthenticationFragment: Fragment() {
    private lateinit var binding: FragmentAuthenticationBinding
    private val viewModel: AuthenticationViewModel by viewModels()

    private val USER_ID = "userID"

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

        viewModel.authStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                AuthApiStatus.SUCCESS -> {
                    findNavController().navigate(R.id.action_authenticationFragment_to_MainFragment)
                }
                AuthApiStatus.FAILURE -> {
                    Toast.makeText(context, "コードが間違っています", Toast.LENGTH_LONG).show()
                }
                AuthApiStatus.LOADING -> {}
            }
        }

        binding.buttonUserID.setOnClickListener {
            val userId = userIdPref.readPrefUserId(USER_ID)
            if (userId != null) {
                viewModel.sendAuthCode(userId)
            }
        }
    }
}