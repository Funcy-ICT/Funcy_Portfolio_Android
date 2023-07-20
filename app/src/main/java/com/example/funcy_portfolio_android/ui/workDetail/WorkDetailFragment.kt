package com.example.funcy_portfolio_android.ui.workDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentWorkDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class WorkDetailFragment : Fragment() {

    private lateinit var binding: FragmentWorkDetailBinding
    private  val viewModel by viewModels<WorkDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWorkDetailBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycle.addObserver(binding.videoView)

        binding.imgGithub.setOnClickListener {
            viewModel.navigateToCustomTab(requireContext())
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
            Glide.with(this).load(it[0].Image).error(R.drawable.img_work_detail_thumbnail).into(binding.imgThumbnail)
        })

        viewModel.youtubeVideoId.observe(viewLifecycleOwner) { youtubeVideoId ->
            binding.videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(youtubeVideoId, 0F)
                }
            })
        }

        viewModel.workDetailStatus.observe(viewLifecycleOwner, Observer { status ->
            when(status!!){
                WorkDetailViewModel.WorkApiStatus.LOADING ->{
                    //ローディングアニメーション？？
                }
                WorkDetailViewModel.WorkApiStatus.DONE ->{
                    viewModel.setWorkDetail()
                }
                WorkDetailViewModel.WorkApiStatus.ERROR ->{
                    Toast.makeText(context, "作品が取得できませんでした", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}