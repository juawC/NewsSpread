package com.juawapps.newsspread.ui.news;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.databinding.NewsItemBinding;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.ui.common.DataBoundRecyclerAdapter;
import com.juawapps.newsspread.web.CustomTabHelper;


public class NewsAdapter extends DataBoundRecyclerAdapter<Article, NewsItemBinding> {

    private final ReadMoreHandler mReadMoreHandler;
    private final DataBindingComponent mDataBindingComponent;

    NewsAdapter(DataBindingComponent dataBindingComponent, CustomTabHelper customTabHelper) {

        mReadMoreHandler = new ReadMoreHandler(customTabHelper);
        mDataBindingComponent = dataBindingComponent;
    }

    @Override
    protected void bind(NewsItemBinding binding, Article item) {
        binding.setArticle(item);
        binding.setReadMoreHandler(mReadMoreHandler);
    }

    @Override
    protected NewsItemBinding createBinding(ViewGroup parent) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        return DataBindingUtil.inflate(layoutInflater,R.layout.news_item, parent,
                false, mDataBindingComponent);
    }
}
