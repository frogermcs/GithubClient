package frogermcs.io.githubclient.ui.activity.presenter;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import frogermcs.io.githubclient.AppProductionComponent;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.data.api.UserModule;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.SplashActivity;
import frogermcs.io.githubclient.utils.SimpleObserver;
import frogermcs.io.githubclient.utils.Validator;
import rx.Observable;
import rx.functions.Func2;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class SplashActivityPresenter {
    public String username;

    private SplashActivity splashActivity;
    private Validator validator;
    private AppProductionComponent appProductionComponent;

    private UserManager userManager;

    public SplashActivityPresenter(SplashActivity splashActivity, Validator validator) {
        this.splashActivity = splashActivity;
        this.appProductionComponent = splashActivity.getAppProductionComponent();
        this.validator = validator;
        Futures.addCallback(appProductionComponent.userManager(), new FutureCallback<UserManager>() {
            @Override
            public void onSuccess(UserManager result) {
                SplashActivityPresenter.this.userManager = result;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void onShowRepositoriesClick() {
        if (userManager == null) {
            return;
        }

        if (validator.validUsername(username)) {
            splashActivity.showLoading(true);
            Observable.combineLatest(
                    Observable.from(appProductionComponent.userModuleFactory()),
                    userManager.getUser(username),
                    new Func2<UserModule.Factory, User, UserModule>() {
                        @Override
                        public UserModule call(UserModule.Factory factory, User user) {
                            return factory.create(user);
                        }
                    }
            )
                    .subscribe(new SimpleObserver<UserModule>() {
                        @Override
                        public void onNext(UserModule userModule) {
                            GithubClientApplication.get(splashActivity).createUserComponent(userModule);

                            splashActivity.showLoading(false);
                            splashActivity.showRepositoriesListForUser();
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
