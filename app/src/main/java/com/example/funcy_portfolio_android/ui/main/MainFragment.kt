package com.example.funcy_portfolio_android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        binding = FragmentMainBinding.inflate(inflater, container, false)
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

        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                // text changed
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                // submit button pressed
                return false
            }
        })

        val Worklist = listOf<WorkData>(
            WorkData(R.drawable.garden_strand, "garden_strand", "ネックレス"),
            WorkData(R.drawable.gatsby_hat, "gatsby_hat","ハット"),
            WorkData(R.drawable.stella_sunglasses, "stella_sunglasses","グラス"),
            WorkData(R.drawable.strut_earrings, "strut_earrings","イヤリング"),
            WorkData(R.drawable.vagabond_sack, "vagabond_sack","リュックサック"),
            WorkData(R.drawable.varsity_socks, "varsity_sovks","ソックス"),
            WorkData(R.drawable.whitey_belt, "whitey_belt","ベルト"),
            WorkData(R.drawable.copper_wire_rack, "whitey_belt","ラック"),
            WorkData(R.drawable.gilt_desk_trio, "whitey_belt","小物入れ"),
            WorkData(R.drawable.shrug_bag, "whitey_belt","バッグ")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = CardAdapter(Worklist)
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }



    /*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // Menuの設定
        inflater.inflate(R.menu.search, menu)

        // app:actionViewClass="android.support.v7.widget.SearchView"のItemの取得
        val menuItem: MenuItem = menu.findItem(R.id.search_menu_search_view)
        /**
         * API level 11以上の場合はこっちを使う
         *
         * this.searchView = (SearchView)menuItem.getActionView();
         */

        // ActionViewの取得
        this.searchView = MenuItemCompat.getActionView(menuItem) as SearchView

        // 虫眼鏡アイコンを最初表示するかの設定
        this.searchView.setIconifiedByDefault(true)

        // Submitボタンを表示するかどうか
        this.searchView.setSubmitButtonEnabled(false)
        if (!this.searchWord.equals("")) {
            // TextView.setTextみたいなもの
            this.searchView.setQuery(this.searchWord, false)
        } else {
            val queryHint: String =
                self.getResources().getString(R.string.search_menu_query_hint_text)
            // placeholderみたいなもの
            this.searchView.setQueryHint(queryHint)
        }
        this.searchView.setOnQueryTextListener(self.onQueryTextListener)
    }

    private val onQueryTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchWord: String): Boolean {
                // SubmitボタンorEnterKeyを押されたら呼び出されるメソッド
                return self.setSearchWord(searchWord)
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // 入力される度に呼び出される
                return false
            }
        }

    private fun setSearchWord(searchWord: String?): Boolean {
        val actionBar: ActionBar = (this.activity as ActionBarActivity?).getSupportActionBar()
        actionBar.setTitle(searchWord)
        actionBar.setDisplayShowTitleEnabled(true)
        if (searchWord != null && searchWord != "") {
            // searchWordがあることを確認
            searchWord = searchWord
        }
        // 虫眼鏡アイコンを隠す
        this.searchView.setIconified(false)
        // SearchViewを隠す
        this.searchView.onActionViewCollapsed()
        // Focusを外す
        this.searchView.clearFocus()
        return false
    }
*/
}