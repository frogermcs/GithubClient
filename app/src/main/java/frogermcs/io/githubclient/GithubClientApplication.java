package frogermcs.io.githubclient;

import android.app.Application;
import android.content.Context;

import com.frogermcs.dagger2metrics.Dagger2Metrics;

import frogermcs.io.githubclient.dependencyinjection.components.AppComponent;
import frogermcs.io.githubclient.dependencyinjection.components.DaggerAppComponent;
import frogermcs.io.githubclient.dependencyinjection.components.UserComponent;
import frogermcs.io.githubclient.dependencyinjection.modules.AppModule;
import frogermcs.io.githubclient.dependencyinjection.modules.UserModule;
import frogermcs.io.githubclient.data.model.User;
import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
    public class GithubClientApplication extends Application {

    private AppComponent appComponent;
    private UserComponent userComponent;

    public static GithubClientApplication get(Context context) {
        return (GithubClientApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Dagger2Metrics.enableCapturing(this);
        }

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public UserComponent createUserComponent(User user) {
        userComponent = appComponent.plus(new UserModule(user));
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

}