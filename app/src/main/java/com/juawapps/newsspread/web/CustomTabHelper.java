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
 * Created by joaocevada on 17/12/2017.
 */

public class CustomTabHelper {

    private CustomTabsIntent mCustomTabsIntent;
    private final Context mContext;
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
        mContext = activity;
        CustomTabsClient.bindCustomTabsService(mContext, CUSTOM_TAB_PACKAGE_NAME, mConnection);
    }

    public void openNewTab(String url) {
        if (mCustomTabsIntent != null) {
            mCustomTabsIntent.launchUrl(mContext, Uri.parse(url));
        } else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(browserIntent);
        }
    }

    private final CustomTabsServiceConnection mConnection = new CustomTabsServiceConnection() {
        @Override
        public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
            client.warmup(0);
            mCustomTabsIntent = buildIntent(client);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mCustomTabsIntent = null;
        }
    };

    private CustomTabsIntent buildIntent(CustomTabsClient customTabsClient) {
        // Create session
        CustomTabsSession customTabsSession = customTabsClient.newSession(null);
        // Built intent
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        builder.setStartAnimations(mContext, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(mContext, R.anim.slide_in_left, R.anim.slide_out_right);
        return builder.build();
    }
}
