package frogermcs.io.githubclient.dependencyinjection.components;

import dagger.Subcomponent;
import frogermcs.io.githubclient.dependencyinjection.scopes.UserScope;
import frogermcs.io.githubclient.dependencyinjection.modules.UserModule;
import frogermcs.io.githubclient.dependencyinjection.modules.RepositoriesListActivityModule;
import frogermcs.io.githubclient.dependencyinjection.modules.RepositoryDetailsActivityModule;

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

    RepositoriesListActivityComponent plus(RepositoriesListActivityModule module);

    RepositoryDetailsActivityComponent plus(RepositoryDetailsActivityModule module);
}