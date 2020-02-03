package com.example.sportzintractivetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aperotechnologies.acenutritionist.search.viewpager.MyPagerAdapter
import com.example.sportzintractivetask.fragments.TeamOneFragment
import com.example.sportzintractivetask.fragments.TeamTwoFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    //private var mainViewModel : MainViewModel?= null

    private val mainViewModel by lazy {
        ViewModelProviders.of(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mainViewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        //mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        mainViewModel?.parseData()

        mainViewModel?.tabTitle?.observe(this, Observer {
            Log.d(TAG,"SportzIntractive : $it")
            val tabTitle = it.toArray()
            setupViewPager(tabTitle[0] as String,tabTitle[1] as String)
            titleTextView.text = "${tabTitle[0]} vs ${tabTitle[1]}"
            //mainViewModel?.tabTitle?.removeObservers(this)
        })

        imageView.setOnClickListener {
            finish()
        }


    }


    private fun setupViewPager(tabOneTitle : String,tabTwoTitle: String) {

        val adapter = MyPagerAdapter(supportFragmentManager)

        var firstFragmet = TeamOneFragment()
        var secondFragmet = TeamTwoFragment()

        adapter.addFragment(firstFragmet, tabOneTitle)
        adapter.addFragment(secondFragmet, tabTwoTitle)


        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)

    }

}
