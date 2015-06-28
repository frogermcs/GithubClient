package frogermcs.io.githubclient.data.api;

import dagger.Subcomponent;
import frogermcs.io.githubclient.data.UserScope;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.component.RepositoriesListActivityComponent;
import frogermcs.io.githubclient.ui.activity.component.RepositoryDetailsActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.activity.module.RepositoryDetailsActivityModule;
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
    RepositoriesListActivityComponent plus(RepositoriesListActivityModule repositoriesListActivityModule);

    RepositoryDetailsActivityComponent plus(RepositoryDetailsActivityModule repositoryDetailsActivityModule);
}
