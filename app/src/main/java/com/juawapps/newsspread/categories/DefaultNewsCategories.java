package com.juawapps.newsspread.categories;

import android.support.v4.util.ArrayMap;

import com.juawapps.newsspread.R;

import java.util.Collection;

/**
 * Default categories definition.
 */

class DefaultNewsCategories {

    private static final String BUSINESS= "business";
    private static final String ENTERTAINMENT= "entertainment";
    private static final String GAMING= "gaming";
    private static final String GENERAL= "general";
    private static final String HEALTH_AND_MEDICAL= "health-and-medical";
    private static final String MUSIC= "music";
    private static final String POLITICS= "politics";
    private static final String SCIENCE_AND_NATURE= "science-and-nature";
    private static final String SPORT= "sport";
    private static final String TECHNOLOGY= "technology";


    private static final ArrayMap<String, NewsCategory> mCategoryLabels;
    static
    {
        mCategoryLabels = new ArrayMap<>(10);
        mCategoryLabels.put(BUSINESS, new NewsCategory(BUSINESS, R.string.business_label));
        mCategoryLabels.put(ENTERTAINMENT, new NewsCategory(ENTERTAINMENT, R.string.entertainment_label));
        mCategoryLabels.put(GAMING, new NewsCategory(GAMING, R.string.gaming_label));
        mCategoryLabels.put(GENERAL, new NewsCategory(GENERAL, R.string.general_label));
        mCategoryLabels.put(HEALTH_AND_MEDICAL, new NewsCategory(HEALTH_AND_MEDICAL, R.string.health_and_medical_label));
        mCategoryLabels.put(MUSIC, new NewsCategory(MUSIC, R.string.music_label));
        mCategoryLabels.put(POLITICS, new NewsCategory(POLITICS, R.string.politics_label));
        mCategoryLabels.put(SCIENCE_AND_NATURE, new NewsCategory(SCIENCE_AND_NATURE, R.string.science_and_nature_label));
        mCategoryLabels.put(SPORT, new NewsCategory(SPORT, R.string.sport_label));
        mCategoryLabels.put(TECHNOLOGY, new NewsCategory(TECHNOLOGY, R.string.technology_label));
    }

    static Collection<NewsCategory> getCategories() {
       return mCategoryLabels.values();
    }

}
