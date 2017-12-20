package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.databinding.NewsItemBinding;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.web.CustomTabHelper;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> mValues = new ArrayList<>();
    private final ReadMoreHandler mReadMoreHandler;
    private final DataBindingComponent mDataBindingComponent;

    NewsAdapter(@NonNull LifecycleOwner viewLifeCycle,
                       DataBindingComponent dataBindingComponent,
                       CustomTabHelper customTabHelper,
                       LiveData<Resource<List<Article>>> liveDataArticles) {

        mReadMoreHandler = new ReadMoreHandler(customTabHelper);
        mDataBindingComponent = dataBindingComponent;
        liveDataArticles.observe(viewLifeCycle, articleResource -> {
            if ((articleResource != null ? articleResource.status : null) == Resource.Status.SUCCESS &&
                    articleResource.data != null) {
                mValues = articleResource.data;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        NewsItemBinding itemBinding =
                DataBindingUtil.inflate(layoutInflater,R.layout.news_item, parent,
                        false, mDataBindingComponent);
        return new NewsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        Article item = mValues.get(position);
        holder.bind(item, mReadMoreHandler);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        NewsViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Article article, ReadMoreHandler readMoreHandler) {
            binding.setArticle(article);
            binding.setReadMoreHandler(readMoreHandler);
            binding.executePendingBindings();
        }
    }
}
