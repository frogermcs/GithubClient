package frogermcs.io.githubclient.utils;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class AnalyticsManager {

    private Application app;

    public AnalyticsManager(Application app) {
        this.app = app;
    }

    public void logScreenView(String screenName) {
        Timber.d("Logged screen name: " + screenName);
    }
}

