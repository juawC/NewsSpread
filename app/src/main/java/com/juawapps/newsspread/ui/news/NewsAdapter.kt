package com.juawapps.newsspread.ui.news

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup

import com.juawapps.newsspread.R
import com.juawapps.newsspread.databinding.NewsItemBinding
import com.juawapps.newsspread.data.objects.Article
import com.juawapps.newsspread.ui.common.DataBoundRecyclerAdapter
import com.juawapps.newsspread.web.CustomTabHelper


class NewsAdapter(private val dataBindingComponent: DataBindingComponent, customTabHelper: CustomTabHelper) : DataBoundRecyclerAdapter<Article, NewsItemBinding>() {

    private val mReadMoreHandler: ReadMoreHandler = ReadMoreHandler(customTabHelper)

    override fun bind(binding: NewsItemBinding, item: Article) {
        binding.article = item
        binding.readMoreHandler = mReadMoreHandler
    }

    override fun createBinding(parent: ViewGroup): NewsItemBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(layoutInflater, R.layout.news_item, parent,
                false, dataBindingComponent)
    }
}
