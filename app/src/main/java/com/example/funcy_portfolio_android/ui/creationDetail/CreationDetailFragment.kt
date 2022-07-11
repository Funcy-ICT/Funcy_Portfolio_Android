package com.example.funcy_portfolio_android.ui.creationDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationDetailBinding


class CreationDetailFragment : Fragment() {

    private lateinit var binding: FragmentCreationDetailBinding
    private  val viewModel by viewModels<CreationDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreationDetailBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

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

        viewModel.tagList.observe(viewLifecycleOwner, Observer {
            viewModel.setEachTag(binding.flexTag, requireContext())
        })

        viewModel.images.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it[0].Image).error(R.drawable.img_creation_detail_thumbnail).into(binding.imgThumbnail)
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