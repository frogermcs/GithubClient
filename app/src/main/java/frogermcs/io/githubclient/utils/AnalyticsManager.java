package frogermcs.io.githubclient.utils;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import frogermcs.io.githubclient.data.UserScope;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
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

