package com.juawapps.newsspread.web;

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

    public CustomTabHelper(Context context) {
        CustomTabsClient.bindCustomTabsService(context, CUSTOM_TAB_PACKAGE_NAME,
                new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                client.warmup(0);
                mCustomTabsIntent = buildIntent(client, context);
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

    private CustomTabsIntent buildIntent(CustomTabsClient customTabsClient, Context context) {
        // Create session
        CustomTabsSession customTabsSession = customTabsClient.newSession(null);
        // Built intent
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        builder.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right);
        return builder.build();
    }
}
