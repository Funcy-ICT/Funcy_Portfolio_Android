package com.example.funcy_portfolio_android.ui.creationRegister

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBinding
import com.example.funcy_portfolio_android.databinding.ItemTagBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class CreationRegisterFragment : Fragment() {
    private val viewModel: CreationRegisterViewModel by activityViewModels()
    private lateinit var binding: FragmentCreationRegisterBinding

    //画像選択
    private var image_launchar = registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) {
        viewModel.saveImage(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreationRegisterBinding.inflate(inflater, container, false)

        val tags = viewModel.resetTag()
        Log.e("resetTag",tags.toString())

        //画像変更
        viewModel.thumbnail.observe(viewLifecycleOwner, Observer {
            binding.rlThumbnail.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            binding.rlThumbnail.adapter = viewModel.thumbnail.value?.let { CreationRegisterCardAdapter(it) }
        })

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

        binding.btAddTag.setOnClickListener {
            val creationRegisterBottomSheet = CreationRegisterBottomSheet()
            activity?.let {
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

            if(tags.size != 0 && viewModel.getTagFlag()){
                val itemTagBinding = DataBindingUtil.inflate<ItemTagBinding>(LayoutInflater.from(requireContext()), R.layout.item_tag, binding.flexTag, true)
                itemTagBinding.chipTag.text = tags[tags.lastIndex]
                itemTagBinding.chipTag.setOnCloseIconClickListener {
                    itemTagBinding.chipTag.visibility = View.GONE
                    viewModel.delTagFlag()
                    viewModel.removeTag(itemTagBinding.chipTag.text as String)
                }
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
                            1,
                            binding.etGitHubLink.text.toString(),
                            binding.etYoutubeLink.text.toString()
                        )

                        findNavController().navigate(R.id.action_CreationRegisterFragment_to_MainFragment)

                    })
                    .setNegativeButton("キャンセル", null)
                    .show()

            }else{
                binding.otfTitle.isErrorEnabled = true
                binding.otfTitle.error = getString(R.string.creation_error_title_null)
            }
        }
    
        binding.btAddImage.setOnClickListener {
            image_launchar.launch(arrayOf("image/*"))
        }
    }
}