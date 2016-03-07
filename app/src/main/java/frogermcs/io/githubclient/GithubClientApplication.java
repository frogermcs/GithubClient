package frogermcs.io.githubclient;

import android.app.Application;
import android.content.Context;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;

import java.util.concurrent.Executors;

import frogermcs.io.githubclient.data.UserComponent;
import frogermcs.io.githubclient.data.api.UserModule;
import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class GithubClientApplication extends Application {

    private AppComponent appComponent;
    private UserComponent userComponent;
    private AppProductionComponent appProductionComponent;

    public static GithubClientApplication get(Context context) {
        return (GithubClientApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            AndroidDevMetrics.initWith(this);
        }

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();


        appProductionComponent = DaggerAppProductionComponent.builder()
                .executor(Executors.newSingleThreadExecutor())
                .appComponent(appComponent)
                .build();
    }

    public UserComponent createUserComponent(UserModule userModule) {
        userComponent = appComponent.plus(userModule);
        return userComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    public AppProductionComponent getAppProductionComponent() {
        return appProductionComponent;
    }

}