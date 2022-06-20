package com.example.funcy_portfolio_android.ui.creationRegister

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBinding
import com.example.funcy_portfolio_android.databinding.ItemTagBinding
import com.example.funcy_portfolio_android.model.TagData


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
            activity?.let {
                creationRegisterBottomSheet.show(
                    it.supportFragmentManager,
                    CreationRegisterBottomSheet.TAG
                )
            }

        }

        val tags = mutableListOf<TagData>()
        val tagObserver = Observer<List<TagData>>{ newTagList ->
            Log.e("[observe]tagName",newTagList.toString())
            tags.clear()
            tags.addAll(newTagList)
            Log.e("tagName",tags.toString())

            if(tags.size != 0){
                val itemTagBinding = DataBindingUtil.inflate<ItemTagBinding>(LayoutInflater.from(requireContext()), R.layout.item_tag, binding.flexTag, true)
                itemTagBinding.tvTag.text = tags[tags.lastIndex].tag
            }
        }
        viewModel.tagList.observe(viewLifecycleOwner, tagObserver)

        /* 作品投稿処理 */
        binding.btRegister.setOnClickListener{
            if(binding.etCreationTitle.text.toString() != "") {
                AlertDialog.Builder(activity)
                    .setTitle("作品を投稿しますか？")
                    .setPositiveButton("登録する", DialogInterface.OnClickListener { dialog, which ->
                        viewModel.registerCreation(
                            binding.etCreationTitle.text.toString(),
                            binding.etCreationDescription.text.toString(),
                            1
                        )
                        val res = viewModel.getRes()
                        Log.e("resviewmodel",res.toString())
                        if(res != "") {
                            findNavController().navigate(R.id.action_CreationRegisterFragment_to_MainFragment)

                            val toast = Toast.makeText(context, "投稿できました", Toast.LENGTH_LONG)
                            toast.show()
                        }else{
                            val toast = Toast.makeText(context, "投稿できませんでした\n もう1度お試しください", Toast.LENGTH_LONG)
                            toast.show()
                        }
                    })
                    .setNegativeButton("キャンセル", null)
                    .show()

            }else{
                binding.otfTitle.isErrorEnabled = true
                binding.otfTitle.error = getString(R.string.creation_error_title_null)
            }
        }
    }

}