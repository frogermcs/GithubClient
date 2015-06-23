package frogermcs.io.githubclient;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import frogermcs.io.githubclient.data.api.GithubApiModule;
import frogermcs.io.githubclient.data.api.UserComponent;
import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.data.api.UserModule;
import frogermcs.io.githubclient.utils.AnalyticsManager;
import frogermcs.io.githubclient.utils.Validator;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                GithubApiModule.class
        }
)
public interface AppComponent {

    //Dependencies below should be visible out of the component

    Application getApplication();

    AnalyticsManager getAnalyticsManager();

    Validator getValidator();

    UserManager getUserManager();

    //Here we're constructing subgraph of AppComponent graph

    UserComponent plus(UserModule userModule);
}
