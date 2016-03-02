package frogermcs.io.githubclient.dependencyinjection.components;

import dagger.Subcomponent;
import frogermcs.io.githubclient.dependencyinjection.scopes.ActivityScope;
import frogermcs.io.githubclient.ui.activity.RepositoriesListActivity;
import frogermcs.io.githubclient.dependencyinjection.modules.RepositoriesListActivityModule;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = RepositoriesListActivityModule.class
)
public interface RepositoriesListActivityComponent {

    RepositoriesListActivity inject(RepositoriesListActivity repositoriesListActivity);

}