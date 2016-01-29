package frogermcs.io.githubclient.ui.activity.presenter;

import com.google.common.collect.ImmutableList;

import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.RepositoriesListUI;
import frogermcs.io.githubclient.utils.SimpleObserver;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class RepositoriesListActivityPresenter {
    private RepositoriesListUI repositoriesListUI;
    private RepositoriesManager repositoriesManager;

    public RepositoriesListActivityPresenter(RepositoriesListUI repositoriesListUI,
                                             RepositoriesManager repositoriesManager) {
        this.repositoriesListUI = repositoriesListUI;
        this.repositoriesManager = repositoriesManager;
    }

    public void loadRepositories() {
        repositoriesListUI.showLoading(true);
        repositoriesManager.getUsersRepositories().subscribe(new SimpleObserver<ImmutableList<Repository>>() {
            @Override
            public void onNext(ImmutableList<Repository> repositories) {
                repositoriesListUI.showLoading(false);
                repositoriesListUI.setRepositories(repositories);
            }

            @Override
            public void onError(Throwable e) {
                repositoriesListUI.showLoading(false);
            }
        });
    }

}
