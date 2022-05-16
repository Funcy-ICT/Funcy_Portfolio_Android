package com.example.funcy_portfolio_android.ui.creationRegister

import android.graphics.Color
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationRegisterBinding

class CreationRegisterFragment : Fragment() {

    private lateinit var binding: FragmentCreationRegisterBinding
    private lateinit var viewModel: CreationRegisterViewModel

    private var image_launchar = registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) {
        viewModel.saveImage(it)
        binding.rlThumbnail.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rlThumbnail.adapter = viewModel.thumbnail.value?.let { CreationRegisterCardAdapter(
            viewModel.thumbnail.value!!
        ) }
        //binding.rlThumbnail.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreationRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CreationRegisterViewModel::class.java)

        //画像反映
        viewModel.thumbnail.observe(viewLifecycleOwner, Observer {
            //binding.rlThumbnail.setBackgroundColor(Color.BLACK)
            //binding.rlThumbnail.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            //binding.rlThumbnail.adapter = viewModel.thumbnail.value?.let { CreationRegisterCardAdapter(it) }
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

        binding.btAddImage.setOnClickListener {
            image_launchar.launch(arrayOf("image/*"))
        }
    }
}