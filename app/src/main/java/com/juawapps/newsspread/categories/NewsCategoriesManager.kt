package com.juawapps.newsspread.categories


import java.util.ArrayList

/**
 * News categories manager.
 *
 *
 * This class only gets the default categories but in the future is to have the categories
 * customizable.
 */

class NewsCategoriesManager {

    val categories: List<NewsCategory>
        get() = ArrayList(DefaultNewsCategories.categories)

}
