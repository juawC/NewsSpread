package com.juawapps.newsspread.web

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.customtabs.CustomTabsClient
import android.support.customtabs.CustomTabsIntent
import android.support.customtabs.CustomTabsServiceConnection
import android.support.v4.content.ContextCompat

import com.juawapps.newsspread.R
import com.juawapps.newsspread.annotation.DebugOpenClass

const val CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome"

/**
 * Manage custom tabs, warming up the service for the tabs to load faster.
 */
@DebugOpenClass
class CustomTabHelper(context: Context) {


    private var mCustomTabsIntent: CustomTabsIntent? = null

    init {
        CustomTabsClient.bindCustomTabsService(context, CUSTOM_TAB_PACKAGE_NAME,
                object : CustomTabsServiceConnection() {
                    override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
                        client.warmup(0)
                        mCustomTabsIntent = buildIntent(client, context)
                    }

                    override fun onServiceDisconnected(name: ComponentName) {
                        mCustomTabsIntent = null
                    }
                })
    }

    fun openNewTab(url: String, context: Context) {
        if (mCustomTabsIntent != null) {
            mCustomTabsIntent?.launchUrl(context, Uri.parse(url))
        } else {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }
    }

    private fun buildIntent(customTabsClient: CustomTabsClient, context: Context): CustomTabsIntent {
        // Create session
        val customTabsSession = customTabsClient.newSession(null)
        // Built intent
        val builder = CustomTabsIntent.Builder(customTabsSession)
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        builder.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
        builder.setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right)
        return builder.build()
    }

}
