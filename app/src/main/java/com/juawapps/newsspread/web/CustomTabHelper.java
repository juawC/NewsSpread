package com.juawapps.newsspread.web;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;

import com.juawapps.newsspread.R;

/**
 * Manage custom tabs, warming up the service for the tabs to load faster.
 */

public class CustomTabHelper {

    private CustomTabsIntent mCustomTabsIntent;
    private static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";

    private static CustomTabHelper sInstance;

    public static CustomTabHelper get(Activity activity) {
        if (sInstance == null) {
            synchronized (CustomTabHelper.class) {
                if (sInstance == null) {
                    sInstance = new CustomTabHelper(activity);
                }
            }
        }
        return sInstance;
    }

    private CustomTabHelper(Activity activity) {
        CustomTabsClient.bindCustomTabsService(activity, CUSTOM_TAB_PACKAGE_NAME,
                new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                client.warmup(0);
                mCustomTabsIntent = buildIntent(client, activity);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mCustomTabsIntent = null;
            }
        });
    }

    public void openNewTab(String url, Context context) {
        if (mCustomTabsIntent != null) {
            mCustomTabsIntent.launchUrl(context, Uri.parse(url));
        } else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
        }
    }

    private CustomTabsIntent buildIntent(CustomTabsClient customTabsClient, Activity activity) {
        // Create session
        CustomTabsSession customTabsSession = customTabsClient.newSession(null);
        // Built intent
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        builder.setStartAnimations(activity, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(activity, R.anim.slide_in_left, R.anim.slide_out_right);
        return builder.build();
    }
}
