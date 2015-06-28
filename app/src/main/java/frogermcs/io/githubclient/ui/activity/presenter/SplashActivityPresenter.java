package frogermcs.io.githubclient.ui.activity.presenter;

import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.SplashActivity;
import frogermcs.io.githubclient.utils.SimpleObserver;
import frogermcs.io.githubclient.utils.Validator;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class SplashActivityPresenter {
    public String username;

    private SplashActivity splashActivity;
    private Validator validator;
    private UserManager userManager;

    public SplashActivityPresenter(SplashActivity splashActivity, Validator validator, UserManager userManager) {
        this.splashActivity = splashActivity;
        this.validator = validator;
        this.userManager = userManager;
    }

    public void onShowRepositoriesClick() {
        if (validator.validUsername(username)) {
            splashActivity.showLoading(true);
            userManager.getUser(username).subscribe(new SimpleObserver<User>() {
                @Override
                public void onNext(User user) {
                    splashActivity.showLoading(false);
                    splashActivity.showRepositoriesListForUser(user);
                }

                @Override
                public void onError(Throwable e) {
                    splashActivity.showLoading(false);
                    splashActivity.showValidationError();
                }
            });
        } else {
            splashActivity.showValidationError();
        }
    }
}
