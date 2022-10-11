package com.example.funcy_portfolio_android.ui.groupList

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.funcy_portfolio_android.R

class GroupListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group_list, container, false)
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