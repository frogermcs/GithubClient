package frogermcs.io.githubclient;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

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
                AppModule.class
        }
)
public interface AppComponent {
    void inject(GithubClientApplication githubClientApplication);

    ApiComponent plus(GithubApiModule apiModule);

    Application getApplication();

    AnalyticsManager getAnalyticsManager();

    Validator getValidator();
}
