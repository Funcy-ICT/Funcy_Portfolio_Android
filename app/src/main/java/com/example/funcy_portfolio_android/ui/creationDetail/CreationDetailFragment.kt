package com.example.funcy_portfolio_android.ui.creationDetail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationDetailBinding
import com.example.funcy_portfolio_android.databinding.ItemTagBinding
import com.google.android.material.chip.Chip


class CreationDetailFragment : Fragment() {

    private lateinit var binding: FragmentCreationDetailBinding
    private  val viewModel by viewModels<CreationDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreationDetailBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvVideoLink.setOnClickListener {
            viewModel.navigateToYouTube(binding.tvVideoLink.text.toString(), requireContext())
        }
        binding.tvGithubLink.setOnClickListener {
            viewModel.navigateToCustomTab(binding.tvGithubLink.text.toString(), requireContext())
        }
        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btEdit.setOnClickListener {
            //editの処理
        }
        binding.btShare.setOnClickListener {
            //shareの処理
        }

        viewModel.tags.observe(viewLifecycleOwner, Observer { tags ->
            val chipGroup = binding.flexTag
            chipGroup.removeAllViews()
            tags.forEach { tagText ->
                val chip = Chip(context)
                chip.text = tagText
                chipGroup.addView(chip)
            }
        })

        viewModel.creationDetailStatus.observe(viewLifecycleOwner, Observer { status ->
            when(status!!){
                CreationApiStatus.LOADING ->{
                    //ローディングアニメーション？？
                }
                CreationApiStatus.DONE ->{
                    viewModel.setCreationDetail()
                }
                CreationApiStatus.ERROR ->{
                    Toast.makeText(context, "作品が取得できませんでした", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}