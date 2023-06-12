package com.example.funcy_portfolio_android.ui.authentication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.funcy_portfolio_android.model.localDataSource.Keys
import com.example.funcy_portfolio_android.model.localDataSource.SharedPref

enum class AuthApiStatus{ INIT, LOADING, SUCCESS, FAILURE}

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
        val sharedPref = SharedPref(requireActivity())

        viewModel.authStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                AuthApiStatus.SUCCESS -> {
                    viewModel.text.value = getString(R.string.comp_registration_message)
                    binding.buttonSendAuthCode.visibility = View.GONE
                    binding.textNotReceive.visibility = View.GONE
                    binding.inputDisplayName.visibility = View.GONE

                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            findNavController().navigate(R.id.action_authenticationFragment_to_MainFragment)
                        }
                        ,2000
                    )
                }
                AuthApiStatus.FAILURE -> {
                    Toast.makeText(context, "コードが間違っています", Toast.LENGTH_LONG).show()
                }
                AuthApiStatus.LOADING -> {}
                AuthApiStatus.INIT -> {
                    viewModel.text.value = getString(R.string.message_send_mail)
                    binding.buttonSendAuthCode.visibility = View.VISIBLE
                    binding.textNotReceive.visibility = View.VISIBLE
                    binding.inputDisplayName.visibility = View.VISIBLE
                }
                else -> {}
            }
        }

        binding.buttonSendAuthCode.setOnClickListener {
            val userId = sharedPref.readSharedPref(Keys.USERID.name)
            if (userId != null) {
                viewModel.sendAuthCode(userId)
            }
        }
    }
}