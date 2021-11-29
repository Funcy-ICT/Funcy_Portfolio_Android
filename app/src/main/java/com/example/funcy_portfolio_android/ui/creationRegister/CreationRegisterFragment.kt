package com.example.funcy_portfolio_android.ui.creationRegister

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBinding
import com.example.funcy_portfolio_android.databinding.ItemTagBinding

class CreationRegisterFragment : Fragment() {
    private val viewModel: CreationRegisterViewModel by activityViewModels()
    private lateinit var binding: FragmentCreationRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreationRegisterBinding.inflate(inflater, container, false)

        val tags = viewModel.resetTag()
        Log.e("resetTag",tags.toString())
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

        binding.btAddImage.setOnClickListener{

        }

        binding.btAddTag.setOnClickListener {
            val creationRegisterBottomSheet = CreationRegisterBottomSheet()
            activity?.let { it ->
                creationRegisterBottomSheet.show(
                    it.supportFragmentManager,
                    CreationRegisterBottomSheet.TAG
                )
            }

        }

        val tags = mutableListOf<String>()


        val tagObserver = Observer<List<String>>{ newTagList ->
            tags.clear()
            tags.addAll(newTagList)
            Log.e("tagName",tags.toString())

            if(tags.size != 0){
                val itemTagBinding = DataBindingUtil.inflate<ItemTagBinding>(LayoutInflater.from(requireContext()), R.layout.item_tag, binding.flexTag, true)
                itemTagBinding.tvTag.text = tags[tags.lastIndex]
//                Log.e("tagtext",)
            }
        }
        viewModel.tagList.observe(viewLifecycleOwner, tagObserver)
    }

}