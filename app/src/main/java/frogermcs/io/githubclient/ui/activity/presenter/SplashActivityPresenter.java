package frogermcs.io.githubclient.ui.activity.presenter;

import android.os.Handler;

import frogermcs.io.githubclient.HeavyLibraryWrapper;
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

    private final SplashActivity splashActivity;
    private final Validator validator;
    private final UserManager userManager;

    public SplashActivityPresenter(SplashActivity splashActivity, Validator validator,
                                   UserManager userManager, HeavyLibraryWrapper heavyLibraryWrapper) {
        this.splashActivity = splashActivity;
        this.validator = validator;
        this.userManager = userManager;

        HeavyLibraryWrapper heavyLibraryWrapper1 = heavyLibraryWrapper;
        //This calls should be delivered to ExternalLibrary right after it will be initialized
        heavyLibraryWrapper1.callMethod();
        heavyLibraryWrapper1.callMethod();
        heavyLibraryWrapper1.callMethod();
        heavyLibraryWrapper1.callMethod();
    }

    public void onShowRepositoriesClick() {
        if (validator.validUsername(username)) {
            splashActivity.showLoading(true);
            userManager.getUser(username).subscribe(new SimpleObserver<User>() {
                @Override
                public void onNext(User user) {
                    // To avoid prematurely hiding the progressbar, post delayed
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override public void run() {
                            splashActivity.showLoading(false);
                        }
                    }, 1000 /* 1 sec */);
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
