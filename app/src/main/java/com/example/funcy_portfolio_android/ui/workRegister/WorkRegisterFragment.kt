package com.example.funcy_portfolio_android.ui.workRegister

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentWorkRegisterBinding
import com.example.funcy_portfolio_android.databinding.ItemTagBinding

class WorkRegisterFragment : Fragment() {
    private val viewModel: WorkRegisterViewModel by activityViewModels()
    private lateinit var binding: FragmentWorkRegisterBinding

    //画像選択
    private var image_launchar = registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) {
        viewModel.saveImage(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkRegisterBinding.inflate(inflater, container, false)

        val tags = viewModel.resetTag()
        Log.e("resetTag",tags.toString())

        //画像変更
        viewModel.thumbnail.observe(viewLifecycleOwner, Observer {
            binding.rlThumbnail.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            binding.rlThumbnail.adapter = viewModel.thumbnail.value?.let { WorkRegisterCardAdapter(it) }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_WorkRegisterFragment_to_MainFragment)
        }

        binding.swPublic.setOnCheckedChangeListener { buttonView, isChecked ->
            when (isChecked) {
                true -> buttonView.text = "公開"
                false -> buttonView.text = "非公開"
            }
        }

        binding.btAddTag.setOnClickListener {
            val workRegisterBottomSheet = WorkRegisterBottomSheet()
            activity?.let {
                workRegisterBottomSheet.show(
                    it.supportFragmentManager,
                    WorkRegisterBottomSheet.TAG
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
            if(binding.etWorkTitle.text.toString() != "") {
                AlertDialog.Builder(activity)
                    .setTitle("作品を投稿しますか？")
                    .setPositiveButton("登録する", DialogInterface.OnClickListener { dialog, which ->
                        viewModel.registerWork(
                            binding.etWorkTitle.text.toString(),
                            binding.etWorkDescription.text.toString(),
                            1,
                            binding.etGitHubLink.text.toString(),
                            binding.etYoutubeLink.text.toString()
                        )

                        findNavController().navigate(R.id.action_WorkRegisterFragment_to_MainFragment)

                    })
                    .setNegativeButton("キャンセル", null)
                    .show()

            }else{
                binding.otfTitle.isErrorEnabled = true
                binding.otfTitle.error = getString(R.string.work_error_title_null)
            }
        }
    
        binding.btAddImage.setOnClickListener {
            image_launchar.launch(arrayOf("image/*"))
        }

        //フォーカスが外れたとき→別の場所おしたときキーボード隠す
        binding.etWorkTitle.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etWorkDescription.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etGitHubLink.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }
        binding.etYoutubeLink.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)showoffKeyboard()
        }

        //Enterキー押したときキーボード隠す
        binding.etWorkTitle.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etGitHubLink.setOnEditorActionListener { _, i, keyEvent ->
            return@setOnEditorActionListener getActions(i,keyEvent)
        }
        binding.etYoutubeLink.setOnEditorActionListener { _, i, keyEvent ->
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