package com.juawapps.newsspread.ui.news;

import android.view.View;

import com.juawapps.newsspread.web.CustomTabHelper;

/**
 * Handler class to manage news items clicks.
 */
@SuppressWarnings("WeakerAccess")
public class ReadMoreHandler {

    private final CustomTabHelper mCustomTabHelper;

    public ReadMoreHandler(CustomTabHelper customTabHelper) {
        mCustomTabHelper = customTabHelper;
    }

    public void onReadMoreClick(String url, View view) {
        mCustomTabHelper.openNewTab(url, view.getContext());
    }
}
