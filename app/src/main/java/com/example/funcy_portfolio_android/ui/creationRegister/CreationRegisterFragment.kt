package com.example.funcy_portfolio_android.ui.creationRegister

import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBinding

class CreationRegisterFragment : Fragment() {

    private lateinit var binding: FragmentCreationRegisterBinding

    private val image_launchar = registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) {
        val adapter = ArrayAdapter<Uri>(requireContext(), android.R.layout.simple_list_item_1, it)
        binding.ivThumbnail1.setImageURI(it[0])
        binding.ivThumbnail2.setImageURI(it[1])
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreationRegisterBinding.inflate(inflater, container, false)
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

        binding.btAddImage.setOnClickListener {
            image_launchar.launch(arrayOf("image/*"))
        }
    }

}