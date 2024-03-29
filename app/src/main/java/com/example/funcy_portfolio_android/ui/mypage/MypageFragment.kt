package com.example.funcy_portfolio_android.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    private lateinit var binding: FragmentMypageBinding
    private val viewModel: MypageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        binding.mypageViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOpenProfile.setOnClickListener {
            val expandableLayout = binding.expandableProfile
            expandableLayout.expand()
            it.visibility = View.GONE
        }
        binding.buttonCloseProfile.setOnClickListener {
            val expandableLayout = binding.expandableProfile
            expandableLayout.collapse()
            binding.buttonOpenProfile.visibility = View.VISIBLE
        }

        binding.buttonOpenSkill.setOnClickListener {
            val expandableLayout = binding.expandableSkill
            expandableLayout.expand()
            it.visibility = View.GONE
        }
        binding.buttonCloseSkill.setOnClickListener {
            val expandableLayout = binding.expandableSkill
            expandableLayout.collapse()
            binding.buttonOpenSkill.visibility = View.VISIBLE
        }

        binding.fabDetail.setOnClickListener {
            findNavController().navigate(R.id.action_MypageFragment_to_WorkRegisterFragment)
        }

//        val Worklist = listOf<WorkData>(
//            WorkData(R.drawable.garden_strand, "garden_strand", "ネックレス"),
//            WorkData(R.drawable.gatsby_hat, "gatsby_hat","ハット"),
//            WorkData(R.drawable.stella_sunglasses, "stella_sunglasses","グラス"),
//            WorkData(R.drawable.strut_earrings, "strut_earrings","イヤリング"),
//            WorkData(R.drawable.vagabond_sack, "vagabond_sack","リュックサック"),
//            WorkData(R.drawable.varsity_socks, "varsity_sovks","ソックス"),
//            WorkData(R.drawable.whitey_belt, "whitey_belt","ベルト"),
//            WorkData(R.drawable.copper_wire_rack, "whitey_belt","ラック"),
//            WorkData(R.drawable.gilt_desk_trio, "whitey_belt","小物入れ"),
//            WorkData(R.drawable.shrug_bag, "whitey_belt","バッグ")
//        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

//        recyclerView.adapter = CardAdapter(Worklist)
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)


    }

}