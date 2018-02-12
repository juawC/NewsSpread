package com.juawapps.newsspread.ui.news

import android.view.View

import com.juawapps.newsspread.web.CustomTabHelper

/**
 * Handler class to manage news items clicks.
 */
class ReadMoreHandler(private val mCustomTabHelper: CustomTabHelper) {

    fun onReadMoreClick(url: String, view: View) {
        mCustomTabHelper.openNewTab(url, view.context)
    }
}
