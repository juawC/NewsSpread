package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.categories.NewsCategory;
import com.juawapps.newsspread.databinding.FragmentNewsListBinding;
import com.juawapps.newsspread.utils.biding.FragmentDataBindingComponent;
import com.juawapps.newsspread.web.CustomTabHelper;


import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;

/**
 * A fragment representing a list of Items.
 */
public class NewsFragment extends DaggerFragment {

    private static final String ARG_NEWS_CATEGORY = "news-category";
    private NewsCategory mNewsCategory;
    private NewsViewModel mNewsViewModel;

    @Inject
    FragmentDataBindingComponent mDataBindingComponent;

    @Inject
    CustomTabHelper mCustomTabHelper;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    public static NewsFragment newInstance(NewsCategory newsCategory) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NEWS_CATEGORY, newsCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mNewsCategory = getArguments().getParcelable(ARG_NEWS_CATEGORY);
        } else {
            Timber.d("Fragment initialized without category!");
        }

        mNewsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NewsViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        mNewsViewModel.setCategory(mNewsCategory.getKey());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentNewsListBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);


        // Bind view model
        binding.setLifecycleOwner(this);
        binding.setRetryCallback(mNewsViewModel);
        binding.setNewsViewModel(mNewsViewModel);

        // Bind article adapter
        NewsAdapter newsAdapter = new NewsAdapter(mDataBindingComponent, mCustomTabHelper);
        binding.setAdapter(newsAdapter);

        return binding.getRoot();
    }
}
