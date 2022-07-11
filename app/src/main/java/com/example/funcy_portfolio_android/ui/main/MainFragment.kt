package com.example.funcy_portfolio_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentMainBinding


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
        binding.worksGrid.adapter= CardAdapter()

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
    }



//    private lateinit var binding: FragmentMainBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val view = inflater.inflate(R.layout.fragment_main, container, false)
//
//        binding = FragmentMainBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }
//

//
////        val Worklist = listOf<WorkData>(
////            WorkData(R.drawable.garden_strand, "garden_strand", "ネックレス"),
////            WorkData(R.drawable.gatsby_hat, "gatsby_hat","ハット"),
////            WorkData(R.drawable.stella_sunglasses, "stella_sunglasses","グラス"),
////            WorkData(R.drawable.strut_earrings, "strut_earrings","イヤリング"),
////            WorkData(R.drawable.vagabond_sack, "vagabond_sack","リュックサック"),
////            WorkData(R.drawable.varsity_socks, "varsity_sovks","ソックス"),
////            WorkData(R.drawable.whitey_belt, "whitey_belt","ベルト"),
////            WorkData(R.drawable.copper_wire_rack, "whitey_belt","ラック"),
////            WorkData(R.drawable.gilt_desk_trio, "whitey_belt","小物入れ"),
////            WorkData(R.drawable.shrug_bag, "whitey_belt","バッグ")
////        )
//
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
//
//        recyclerView.adapter = CardAdapter(Worklist)
//        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
//    }

}