package frogermcs.io.githubclient.ui.activity.presenter;

import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.RepositoryDetailsActivity;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class RepositoryDetailsActivityPresenter {
    private RepositoryDetailsActivity repositoryDetailsActivity;
    private User user;

    public RepositoryDetailsActivityPresenter(RepositoryDetailsActivity repositoryDetailsActivity, User user) {
        this.repositoryDetailsActivity = repositoryDetailsActivity;
        this.user = user;
    }

    public void init() {
        repositoryDetailsActivity.setupUserName(user.login);
    }
}
