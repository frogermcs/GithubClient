package frogermcs.io.githubclient.ui.activity.module;

import dagger.Module;
import dagger.Provides;
import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.RepositoriesListUI;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class RepositoriesListActivityModule {
    private RepositoriesListUI repositoriesListUI;

    public RepositoriesListActivityModule(RepositoriesListUI repositoriesListUI) {
        this.repositoriesListUI = repositoriesListUI;
    }

    @Provides
    @ActivityScope
    RepositoriesListUI provideRepositoriesListActivity() {
        return repositoriesListUI;
    }

    @Provides
    @ActivityScope
    RepositoriesListActivityPresenter provideRepositoriesListActivityPresenter(RepositoriesManager repositoriesManager) {
        return new RepositoriesListActivityPresenter(repositoriesListUI, repositoriesManager);
    }
}