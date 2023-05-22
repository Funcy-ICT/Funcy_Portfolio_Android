package com.example.funcy_portfolio_android.ui.groupMypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupMypageBinding
import com.example.funcy_portfolio_android.model.WorkData

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

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvGroupCreationList)

        recyclerView.adapter = CardAdapterBefore(Worklist)
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }
}