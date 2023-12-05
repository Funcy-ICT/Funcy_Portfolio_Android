package com.example.funcy_portfolio_android.ui.groupMypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupMypageBinding

class GroupMypageFragment : Fragment() {

    private lateinit var binding: FragmentGroupMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_group_mypage, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val Worklist = listOf<WorkDataList>(
//            WorkDataList(R.drawable.garden_strand, "garden_strand", "ネックレス"),
//            WorkDataList(R.drawable.gatsby_hat, "gatsby_hat","ハット"),
//            WorkDataList(R.drawable.stella_sunglasses, "stella_sunglasses","グラス"),
//            WorkDataList(R.drawable.strut_earrings, "strut_earrings","イヤリング"),
//            WorkDataList(R.drawable.vagabond_sack, "vagabond_sack","リュックサック"),
//            WorkDataList(R.drawable.varsity_socks, "varsity_sovks","ソックス"),
//            WorkDataList(R.drawable.whitey_belt, "whitey_belt","ベルト"),
//            WorkDataList(R.drawable.copper_wire_rack, "whitey_belt","ラック"),
//            WorkDataList(R.drawable.gilt_desk_trio, "whitey_belt","小物入れ"),
//            WorkDataList(R.drawable.shrug_bag, "whitey_belt","バッグ")
//        )

//        val recyclerView = view.findViewById<RecyclerView>(R.id.rvGroupWorkList)
//
//        recyclerView.adapter = CardAdapterBefore(Worklist)
//        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }
}