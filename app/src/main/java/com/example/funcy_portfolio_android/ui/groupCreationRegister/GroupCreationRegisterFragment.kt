package com.example.funcy_portfolio_android.ui.groupCreationRegister

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupCreationRegisterBinding

class GroupCreationRegisterFragment : Fragment(){

    private lateinit var binding:FragmentGroupCreationRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_creation_register, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etGroupCreationTitle.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etGroupCreationDescription.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etGroupGitHubLink.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etGroupYoutubeLink.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }

        binding.etGroupCreationTitle.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etGroupGitHubLink.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etGroupYoutubeLink.setOnEditorActionListener { _, i, keyEvent ->
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