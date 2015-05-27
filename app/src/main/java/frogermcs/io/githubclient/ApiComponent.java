package frogermcs.io.githubclient;

import dagger.Subcomponent;
import frogermcs.io.githubclient.data.ApiScope;
import frogermcs.io.githubclient.data.api.GithubApiModule;
import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.utils.AnalyticsManager;
import frogermcs.io.githubclient.utils.Validator;

/**
 * Created by Miroslaw Stanek on 25.05.15.
 */
@ApiScope
@Subcomponent(
        modules = GithubApiModule.class
)
public interface ApiComponent {
    UserManager getUserManager();

    RepositoriesManager getRepositoriesManager();

    AnalyticsManager getAnalyticsManager();

    Validator getValidator();
}
