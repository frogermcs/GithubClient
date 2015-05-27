package frogermcs.io.githubclient.ui.activity.component;

import dagger.Component;
import frogermcs.io.githubclient.AppComponent;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.RepositoryDetailsActivity;
import frogermcs.io.githubclient.ui.activity.module.RepositoryDetailsActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Component(
        modules = RepositoryDetailsActivityModule.class,
        dependencies = AppComponent.class
)
public interface RepositoryDetailsActivityComponent {
    RepositoryDetailsActivity inject(RepositoryDetailsActivity repositoryDetailsActivity);
    
    RepositoryDetailsActivityPresenter presenter();
}