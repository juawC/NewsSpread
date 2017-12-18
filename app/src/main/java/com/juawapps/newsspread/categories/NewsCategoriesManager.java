package com.juawapps.newsspread.categories;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaocevada on 09/12/2017.
 */

public class NewsCategoriesManager {

    public List<NewsCategory> getCategories() {
        return new ArrayList<>(DefaultNewsCategories.getCategories());}

}
