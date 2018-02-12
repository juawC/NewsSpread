package com.juawapps.newsspread.ui.news

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.juawapps.newsspread.R
import com.juawapps.newsspread.categories.NewsCategory
import com.juawapps.newsspread.databinding.FragmentNewsListBinding
import com.juawapps.newsspread.utils.biding.FragmentDataBindingComponent
import com.juawapps.newsspread.web.CustomTabHelper


import javax.inject.Inject

import dagger.android.support.DaggerFragment

/**
 * A fragment representing a list of Items.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class NewsFragment : DaggerFragment() {
    lateinit var mNewsCategory: NewsCategory
    lateinit var mNewsViewModel: NewsViewModel

    @Inject
    lateinit var mDataBindingComponent: FragmentDataBindingComponent

    @Inject
    lateinit var mCustomTabHelper: CustomTabHelper

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNewsCategory = arguments?.getParcelable(ARG_NEWS_CATEGORY) ?: NewsCategory("", 0)
        mNewsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NewsViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        mNewsViewModel.setCategory(mNewsCategory.key)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentNewsListBinding>(inflater, R.layout.fragment_news_list, container, false)

        // Bind view model
        binding.setLifecycleOwner(this)
        binding.retryCallback = mNewsViewModel
        binding.newsViewModel = mNewsViewModel

        // Bind article adapter
        val newsAdapter = NewsAdapter(mDataBindingComponent, mCustomTabHelper)
        binding.adapter = newsAdapter

        return binding.root
    }

    companion object {

        private val ARG_NEWS_CATEGORY = "news-category"

        fun newInstance(newsCategory: NewsCategory): NewsFragment {
            return NewsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_NEWS_CATEGORY, newsCategory)
                }
            }
        }
    }
}
