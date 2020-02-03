package com.example.sportzintractivetask.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportzintractivetask.MainViewModel

import com.example.sportzintractivetask.R
import com.example.sportzintractivetask.adapter.PlayerRecyclerView
import kotlinx.android.synthetic.main.fragment_team_one.view.*

/**
 * A simple [Fragment] subclass.
 */
class TeamTwoFragment : Fragment() {

    private val TAG = "TeamOneFragment"

    private val mainViewModel by lazy {
        activity?.let {
            ViewModelProviders.of(it)[MainViewModel::class.java]
        }

    }

    var playerAdapter : PlayerRecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_team_two, container, false)

        activity?.let{ context ->
            mainViewModel?.teamTwo?.observeForever( Observer {
                Log.d(TAG,"SportzIntractive : $it")
                playerAdapter = PlayerRecyclerView(it)
                view.recyclerView.layoutManager = LinearLayoutManager(context)
                view.recyclerView.adapter = playerAdapter
                playerAdapter?.notifyDataSetChanged()
            })
        }


        return view
    }


}
