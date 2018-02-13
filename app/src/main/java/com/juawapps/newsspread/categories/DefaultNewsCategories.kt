package com.juawapps.newsspread.categories

import android.support.v4.util.ArrayMap

import com.juawapps.newsspread.R

/**
 * Default categories definition.
 */

const val BUSINESS = "business"
const val ENTERTAINMENT = "entertainment"
const val GAMING = "gaming"
const val GENERAL = "general"
const val HEALTH_AND_MEDICAL = "health-and-medical"
const val MUSIC = "music"
const val POLITICS = "politics"
const val SCIENCE_AND_NATURE = "science-and-nature"
const val SPORT = "sport"
const val TECHNOLOGY = "technology"

internal object DefaultNewsCategories {

    private val mCategoryLabels: ArrayMap<String, NewsCategory> = ArrayMap(10)

    val categories: Collection<NewsCategory>
        get() = mCategoryLabels.values

    init {
        mCategoryLabels.put(BUSINESS, NewsCategory(BUSINESS, R.string.business_label))
        mCategoryLabels.put(ENTERTAINMENT, NewsCategory(ENTERTAINMENT, R.string.entertainment_label))
        mCategoryLabels.put(GAMING, NewsCategory(GAMING, R.string.gaming_label))
        mCategoryLabels.put(GENERAL, NewsCategory(GENERAL, R.string.general_label))
        mCategoryLabels.put(HEALTH_AND_MEDICAL, NewsCategory(HEALTH_AND_MEDICAL, R.string.health_and_medical_label))
        mCategoryLabels.put(MUSIC, NewsCategory(MUSIC, R.string.music_label))
        mCategoryLabels.put(POLITICS, NewsCategory(POLITICS, R.string.politics_label))
        mCategoryLabels.put(SCIENCE_AND_NATURE, NewsCategory(SCIENCE_AND_NATURE, R.string.science_and_nature_label))
        mCategoryLabels.put(SPORT, NewsCategory(SPORT, R.string.sport_label))
        mCategoryLabels.put(TECHNOLOGY, NewsCategory(TECHNOLOGY, R.string.technology_label))
    }

}
