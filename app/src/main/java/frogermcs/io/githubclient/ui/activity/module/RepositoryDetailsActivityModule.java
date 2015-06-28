package frogermcs.io.githubclient.ui.activity.module;

import dagger.Module;
import dagger.Provides;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.RepositoryDetailsActivity;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class RepositoryDetailsActivityModule {
    private RepositoryDetailsActivity repositoryDetailsActivity;

    public RepositoryDetailsActivityModule(RepositoryDetailsActivity repositoryDetailsActivity) {
        this.repositoryDetailsActivity = repositoryDetailsActivity;
    }

    @Provides
    @ActivityScope
    RepositoryDetailsActivity provideRepositoryDetailsActivity() {
        return repositoryDetailsActivity;
    }

    @Provides
    @ActivityScope
    RepositoryDetailsActivityPresenter provideRepositoryDetailsActivityPresenter(User user) {
        return new RepositoryDetailsActivityPresenter(repositoryDetailsActivity, user);
    }
}