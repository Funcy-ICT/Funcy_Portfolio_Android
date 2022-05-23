package com.example.funcy_portfolio_android.ui.creationRegister

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreationRegisterBottomSheet : BottomSheetDialogFragment() {
    private val viewModel: CreationRegisterViewModel by activityViewModels()
    private lateinit var binding: FragmentCreationRegisterBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreationRegisterBottomSheetBinding.inflate(inflater, container, false)

        binding.bottomSheet.let{
            BottomSheetBehavior.from(it).apply {
                state = BottomSheetBehavior.STATE_HALF_EXPANDED
                skipCollapsed = true
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAddTagName.setOnClickListener {
            val etTagNameTrim = binding.etTagName.text.toString().trim()
            if(etTagNameTrim != ""){
                viewModel.setTag(etTagNameTrim)
            }
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(),theme)

        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomSheet =
                (dialog as BottomSheetDialog).findViewById<ConstraintLayout>(R.id.bottomSheet)
            bottomSheet.let {
                if (bottomSheet != null) {
                    BottomSheetBehavior.from(bottomSheet).apply {
                        isDraggable = false
                        skipCollapsed = false
                        state = BottomSheetBehavior.STATE_EXPANDED

                    }
                }
            }
        }


        return bottomSheetDialog
    }

    companion object {
        const val TAG = "CreationRegisterBottomSheet"
    }


}