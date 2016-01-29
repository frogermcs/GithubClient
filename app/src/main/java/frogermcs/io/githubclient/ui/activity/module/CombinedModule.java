package frogermcs.io.githubclient.ui.activity.module;

import dagger.Module;
import dagger.Provides;
import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.DetailUi;
import frogermcs.io.githubclient.ui.activity.RepositoriesListUI;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;

@Module
public class CombinedModule {
    private RepositoriesListUI listUI;
    private DetailUi detailUi;

    public CombinedModule(RepositoriesListUI listUI, DetailUi detailUi) {
        this.listUI = listUI;
        this.detailUi = detailUi;
    }

    @Provides
    @ActivityScope
    RepositoriesListUI provideRepositoriesListActivity() {
        return listUI;
    }

    @Provides
    @ActivityScope
    RepositoriesListActivityPresenter provideRepositoriesListActivityPresenter(RepositoriesManager repositoriesManager) {
        return new RepositoriesListActivityPresenter(listUI, repositoriesManager);
    }

    @Provides
    @ActivityScope
    DetailUi provideRepositoryDetailsActivity() {
        return detailUi;
    }

    @Provides
    @ActivityScope
    RepositoryDetailsActivityPresenter provideRepositoryDetailsActivityPresenter(User user) {
        return new RepositoryDetailsActivityPresenter(detailUi, user);
    }
}