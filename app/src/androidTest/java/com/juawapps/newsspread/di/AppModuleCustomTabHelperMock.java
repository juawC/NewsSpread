package com.juawapps.newsspread.di;


import com.juawapps.newsspread.web.CustomTabHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 * Initialization of objects used throughout the app.
 */

@Module
public class AppModuleCustomTabHelperMock {

    @Singleton
    @Provides
    CustomTabHelper providesCustomTabHelper() {
        CustomTabHelper customTabHelper = mock(CustomTabHelper.class);

        // Mock CustomTabHelper
        doNothing().when(customTabHelper).openNewTab(anyString(), anyObject());
        return customTabHelper;
    }
}
