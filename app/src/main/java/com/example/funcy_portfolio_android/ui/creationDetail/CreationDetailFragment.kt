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
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.findNavController
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentCreationDetailBinding


class CreationDetailFragment : Fragment() {

    private lateinit var binding: FragmentCreationDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreationDetailBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_CreationDetailFragment_to_MainFragment)
        }
        binding.tvVideoLink.setOnClickListener {
            navigateToYouTube(binding.tvVideoLink.text.toString(), requireContext())
        }
        binding.tvGithubLink.setOnClickListener {
            navigateToCustomTab(binding.tvGithubLink.text.toString(), requireContext())
        }
        binding.btBack.setOnClickListener {
            //findNavController().navigate(R.id.action_CreationDetailFragment_to_MainFragment)
            //固定の画面でなく，ちゃんと一つ前の画面に戻る感じで
        }
        binding.btEdit.setOnClickListener {
            //editの処理
        }
        binding.btShare.setOnClickListener {
            //shareの処理
        }
    }

    /** YouTube */
    private fun navigateToYouTube(url: String, context: Context) {
        val uri = Uri.parse(url)
        try {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            navigateToCustomTab(url, context)
        }
    }

    /** GitHub用 CustomTab */
    private fun navigateToCustomTab(url: String, context: Context) {
        val uri = Uri.parse(url)
        CustomTabsIntent.Builder().also { builder ->
            builder.setShowTitle(true)
            builder.build().also {
                it.launchUrl(context, uri)
            }
        }
    }

}