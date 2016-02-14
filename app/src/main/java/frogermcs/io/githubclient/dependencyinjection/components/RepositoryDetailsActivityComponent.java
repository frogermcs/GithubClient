package frogermcs.io.githubclient.dependencyinjection.components;

import dagger.Subcomponent;
import frogermcs.io.githubclient.dependencyinjection.scopes.ActivityScope;
import frogermcs.io.githubclient.ui.activity.RepositoryDetailsActivity;
import frogermcs.io.githubclient.dependencyinjection.modules.RepositoryDetailsActivityModule;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = RepositoryDetailsActivityModule.class
)
public interface RepositoryDetailsActivityComponent {

    RepositoryDetailsActivity inject(RepositoryDetailsActivity repositoryDetailsActivity);

}