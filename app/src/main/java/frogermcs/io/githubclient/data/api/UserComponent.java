package frogermcs.io.githubclient.data.api;

import dagger.Subcomponent;
import frogermcs.io.githubclient.data.UserScope;
import frogermcs.io.githubclient.utils.AnalyticsManager;
import frogermcs.io.githubclient.utils.Validator;

/**
 * Created by Miroslaw Stanek on 23.06.15.
 */
@UserScope
@Subcomponent(
        modules = {
                UserModule.class
        }
)
public interface UserComponent {
    RepositoriesManager getRepositoriesManager();

    AnalyticsManager getAnalyticsManager();

    Validator getValidator();
}
