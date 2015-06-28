package frogermcs.io.githubclient;

import android.app.Application;
import android.content.Context;

import frogermcs.io.githubclient.data.DataComponent;
import frogermcs.io.githubclient.data.api.GithubApiModule;
import frogermcs.io.githubclient.data.api.UserComponent;
import frogermcs.io.githubclient.data.api.UserModule;
import frogermcs.io.githubclient.data.model.User;
import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class GithubClientApplication extends Application {

    private AppComponent appComponent;
    private DataComponent dataComponent;
    private UserComponent userComponent;

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
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        dataComponent = appComponent.plus(new GithubApiModule());
    }

    public UserComponent createUserComponent(User user) {
        userComponent = dataComponent.plus(new UserModule(user));
        return userComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}