package com.juawapps.newsspread.ui.news;

import com.juawapps.newsspread.web.CustomTabHelper;

/**
 * Created by joaocevada on 10/12/2017.
 */

public class ReadMoreHandler {

    private final CustomTabHelper mCustomTabHelper;

    public ReadMoreHandler(CustomTabHelper customTabHelper) {
        mCustomTabHelper = customTabHelper;
    }

    public void onReadMoreClick(String url) {
        mCustomTabHelper.openNewTab(url);
    }
}
