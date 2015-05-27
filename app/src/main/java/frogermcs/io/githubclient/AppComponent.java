package frogermcs.io.githubclient;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import frogermcs.io.githubclient.data.api.GithubApiModule;
import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.data.api.UserManager;
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
    void inject(GithubClientApplication githubClientApplication);

    Application getApplication();

    AnalyticsManager getAnalyticsManager();

    Validator getValidator();

    UserManager getUserManager();

    RepositoriesManager getRepositoriesManager();
}
