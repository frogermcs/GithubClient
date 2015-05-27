package frogermcs.io.githubclient;

import android.app.Application;
import android.content.Context;

import frogermcs.io.githubclient.data.api.GithubApiModule;
import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class GithubClientApplication extends Application {

    private AppComponent appComponent;

    public static GithubClientApplication get(Context context) {
        return (GithubClientApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        //Global dependencies graph is created here
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .githubApiModule(new GithubApiModule()) //Can be removed because of no-arg constructor
                .build();
    }

    //Just a helper to provide appComponent to local components which depend on it
    public AppComponent getAppComponent() {
        return appComponent;
    }

}