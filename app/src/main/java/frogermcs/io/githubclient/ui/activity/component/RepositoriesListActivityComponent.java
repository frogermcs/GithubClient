package frogermcs.io.githubclient.ui.activity.component;

import dagger.Component;
import frogermcs.io.githubclient.data.api.UserComponent;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.RepositoriesListActivity;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Component(
        modules = RepositoriesListActivityModule.class,
        dependencies = UserComponent.class
)
public interface RepositoriesListActivityComponent {
    RepositoriesListActivity inject(RepositoriesListActivity repositoriesListActivity);

    RepositoriesListActivityPresenter presenter();
}