package com.example.funcy_portfolio_android.ui.groupList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.FragmentGroupListBinding

class GroupListFragment : Fragment() {

    private lateinit var binding: FragmentGroupListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_group_list, container, false)

        binding = FragmentGroupListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val groupNames : ArrayList<String> = arrayListOf(
        "Group Name", "Group Name", "Group Name", "Group Name"
    )

    private val groupIcons : ArrayList<Int> = arrayListOf(
        R.drawable.primary_color, R.drawable.primary_color, R.drawable.primary_color, R.drawable.primary_color
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var rvGroup : RecyclerView = view.findViewById(R.id.rvGroup)
        val rLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

        rvGroup.layoutManager = rLayoutManager
        rvGroup.adapter = GroupListAdapter(groupIcons, groupNames)

        rvGroup.setHasFixedSize(true)

    }
}