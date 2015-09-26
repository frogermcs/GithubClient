package frogermcs.io.githubclient.ui.activity.module;

import dagger.Provides;
import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.SplashActivity;
import frogermcs.io.githubclient.ui.activity.presenter.SplashActivityPresenter;
import frogermcs.io.githubclient.utils.Validator;

import static org.mockito.Mockito.mock;

/**
 * Created by Miroslaw Stanek on 20.09.15.
 */
public class MockSplashActivityModule extends SplashActivityModule {

    public MockSplashActivityModule(SplashActivity splashActivity) {
        super(splashActivity);
    }

    @Provides
    @ActivityScope
    SplashActivityPresenter provideSplashActivityPresenter(Validator validator, UserManager userManager) {
        return mock(SplashActivityPresenter.class);
    }
}
