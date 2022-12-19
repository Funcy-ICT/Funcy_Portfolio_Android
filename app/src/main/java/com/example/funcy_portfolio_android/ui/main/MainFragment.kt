package com.example.funcy_portfolio_android.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentMainBinding
import com.example.funcy_portfolio_android.databinding.ItemTagBinding
import com.example.funcy_portfolio_android.databinding.ItemTagWithouticonBinding
import com.example.funcy_portfolio_android.ui.creationRegister.CreationRegisterCardAdapter
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.worksGrid.adapter = CardAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabDetail.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_CreationDetailFragment)
        }

        binding.fabRegister.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_CreationRegisterFragment)
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                //テキスト変更時
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                //検索ボタンクリック時
                viewModel.search(query, 0)
                binding.tvResultTitle.visibility = View.VISIBLE
                binding.tvSearchWord.visibility = View.VISIBLE
                return false
            }
        })

        viewModel.searchFlag.observe(viewLifecycleOwner, Observer {
            if(!it){Toast.makeText(context, "検索結果がありません", Toast.LENGTH_SHORT).show()}
        })


        /** 検索結果 **/
        //10件chipを作成
        for (i in if(viewModel.tags.value!!.size<9){viewModel.tags.value!!.indices}else{0..7}) {
            val tagBinding = DataBindingUtil.inflate<ItemTagWithouticonBinding>(LayoutInflater.from(requireContext()), R.layout.item_tag_withouticon, binding.chipTag, true)
            tagBinding.chipTag.text = viewModel.tags.value!![i]
            tagBinding.chipTag.setOnClickListener {
                /** クリック時の検索処理 **/
            }
        }

        binding.tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // タブが選択された際に呼ばれる
                viewModel.query.value?.let { viewModel.search(it, tab.position) }
                when(tab.position) { //確認用のwhen
                    0 -> {
                        Log.i("hoge","作品")
                    }
                    1 -> {
                        Log.i("hoge","ユーザー")
                    }
                    2 -> {
                        Log.i("hoge","グループ")
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                // タブが未選択になった際に呼ばれる
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
                // 同じタブが選択された際に呼ばれる
            }
        })
    }
}
