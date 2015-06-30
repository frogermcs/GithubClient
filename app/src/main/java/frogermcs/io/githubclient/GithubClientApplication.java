package frogermcs.io.githubclient;

import android.app.Application;
import android.content.Context;

import frogermcs.io.githubclient.data.UserComponent;
import frogermcs.io.githubclient.data.api.UserModule;
import frogermcs.io.githubclient.data.model.User;
import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class GithubClientApplication extends Application {

    private AppComponent appComponent;
    private UserComponent userComponent;

    //...

    public UserComponent createUserComponent(User user) {
        userComponent = appComponent.plus(new UserModule(user));
        return userComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    //...
}