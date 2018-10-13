package com.betterride.bradmin.viewcontrollers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.betterride.bradmin.R
import com.betterride.bradmin.viewcontrollers.fragments.OperatorsFragment
import com.betterride.bradmin.viewcontrollers.fragments.ProfileFragment
import com.betterride.bradmin.viewcontrollers.fragments.ProjectsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when(position){
                0 ->{
                    return ProjectsFragment()
                }
                1 ->{
                    return OperatorsFragment()
                }
                2 -> {
                    return ProfileFragment()
                }
            }
            return ProjectsFragment()
        }

        override fun getCount(): Int {
            return 3
        }
    }
}
