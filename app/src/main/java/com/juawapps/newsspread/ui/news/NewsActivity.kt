package com.juawapps.newsspread.ui.news

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.Toolbar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.os.Bundle

import com.juawapps.newsspread.R
import com.juawapps.newsspread.categories.NewsCategoriesManager
import com.juawapps.newsspread.categories.NewsCategory

import dagger.android.support.DaggerAppCompatActivity

class NewsActivity : DaggerAppCompatActivity() {


    private val mCategoriesManager = NewsCategoriesManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar).apply {
            setLogo(R.mipmap.ic_launcher)
        })

        // Set up the ViewPager with the sections adapter.
        findViewById<ViewPager>(R.id.container).also {
            findViewById<TabLayout>(R.id.tabs).setupWithViewPager(it)
        }.adapter = SectionsPagerAdapter(supportFragmentManager)
    }


    private inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        val mNewsCategories: List<NewsCategory> = mCategoriesManager.categories

        override fun getItem(position: Int): Fragment =
                NewsFragment.newInstance(
                        mNewsCategories[
                                if (position < mNewsCategories.size)
                                    position
                                else
                                    mNewsCategories.size - 1
                                ])


        override fun getCount() = mNewsCategories.size

        override fun getPageTitle(position: Int): String = getString(mNewsCategories[position].label)
    }
}
