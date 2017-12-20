package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.categories.NewsCategory;
import com.juawapps.newsspread.databinding.FragmentNewsListBinding;
import com.juawapps.newsspread.utils.biding.FragmentDataBindingComponent;
import com.juawapps.newsspread.utils.viewmodel.ViewModelFactory;
import com.juawapps.newsspread.web.CustomTabHelper;

import timber.log.Timber;

/**
 * A fragment representing a list of Items.
 */
public class NewsFragment extends Fragment {

    private static final String ARG_NEWS_CATEGORY = "news-category";
    private NewsCategory mNewsCategory;
    private NewsViewModel mNewsViewModel;
    private final DataBindingComponent mDataBindingComponent = new FragmentDataBindingComponent(this);
    private CustomTabHelper mCustomTabHelper;

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

        mNewsViewModel = ViewModelProviders.of(this,
                ViewModelFactory.getInstance(getActivity().getApplication())).get(NewsViewModel.class);
        mCustomTabHelper = CustomTabHelper.get(getActivity());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentNewsListBinding binding =
                FragmentNewsListBinding.inflate(inflater, container, false);

        binding.setAdapter(new NewsAdapter(this, mDataBindingComponent,
                mCustomTabHelper,
                mNewsViewModel.getArticleByCategory(mNewsCategory.getKey())));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);

        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(),
                R.drawable.line_divider);
        dividerItemDecoration.setDrawable(dividerDrawable);
        binding.setDecoration(dividerItemDecoration);
        return binding.getRoot();
    }
}
