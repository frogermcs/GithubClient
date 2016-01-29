package frogermcs.io.githubclient.ui.activity.presenter;

import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.DetailUi;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class RepositoryDetailsActivityPresenter {
    private DetailUi detailUi;
    private User user;

    public RepositoryDetailsActivityPresenter(DetailUi detailUi, User user) {
        this.detailUi = detailUi;
        this.user = user;
    }

    public void init() {
        detailUi.setupUserName(user.login);
    }
}
