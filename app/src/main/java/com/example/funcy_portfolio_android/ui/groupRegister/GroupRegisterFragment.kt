package com.example.funcy_portfolio_android.ui.groupRegister

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
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
        binding.btRegisterGroupWork.setOnClickListener {
            findNavController().navigate(R.id.action_GroupRegisterFragment_to_groupWorkRegisterFragment)
        }
        binding.btGroupMypage.setOnClickListener {
            findNavController().navigate(R.id.action_GroupRegisterFragment_to_groupMypageFragment)
        }

        binding.etGroupName.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etGroupDiscription.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etGroupMailAddress.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }

        binding.etGroupName.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etGroupMailAddress.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
    }
    private fun getActions(i:Int,keyEvent: KeyEvent?):Boolean{
        if( i== EditorInfo.IME_ACTION_SEARCH||
            i== EditorInfo.IME_ACTION_DONE||
            keyEvent!=null&&
            keyEvent.action == KeyEvent.ACTION_DOWN&&
            keyEvent.keyCode== KeyEvent.KEYCODE_ENTER){
            if(keyEvent==null||!keyEvent.isShiftPressed){
                binding.root.requestFocus()
                return true
            }else{
                return false
            }
        }else{
            return false
        }
    }

    private fun showoffKeyboard(){
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}