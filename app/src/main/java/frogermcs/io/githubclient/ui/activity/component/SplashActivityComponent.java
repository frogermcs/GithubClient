package frogermcs.io.githubclient.ui.activity.component;

import dagger.Component;
import frogermcs.io.githubclient.AppComponent;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.SplashActivity;
import frogermcs.io.githubclient.ui.activity.module.SplashActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.SplashActivityPresenter;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Component(
        modules = SplashActivityModule.class,
        dependencies = AppComponent.class
)
public interface SplashActivityComponent {
    SplashActivity inject(SplashActivity splashActivity);

    SplashActivityPresenter presenter();
}