package com.juawapps.newsspread.categories;


import java.util.ArrayList;
import java.util.List;

/**
 * News categories manager.
 * <p>
 * This class only gets the default categories but in the future is to have the categories
 * customizable.
 */

public class NewsCategoriesManager {

    public List<NewsCategory> getCategories() {
        return new ArrayList<>(DefaultNewsCategories.getCategories());}

}
