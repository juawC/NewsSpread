package com.juawapps.newsspread.ui.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting

import com.juawapps.newsspread.data.ArticlesRepository
import com.juawapps.newsspread.data.Resource
import com.juawapps.newsspread.data.objects.Article
import com.juawapps.newsspread.ui.common.RetryCallback

import javax.inject.Inject


class NewsViewModel @Inject
constructor(articlesRepository: ArticlesRepository) : ViewModel(), RetryCallback {

    private val mCategory = MutableLiveData<String>()
    val articles: LiveData<Resource<List<Article>>> =
            Transformations.switchMap(mCategory, { articlesRepository.getArticles(it) })

    fun setCategory(category: String) {

        if (category != mCategory.value) {
            mCategory.postValue(category)
        }
    }

    @VisibleForTesting
    override fun onRetry() {

        val current = mCategory.value
        if (current?.isNotEmpty() == true) {
            mCategory.postValue(current)
        }
    }
}
