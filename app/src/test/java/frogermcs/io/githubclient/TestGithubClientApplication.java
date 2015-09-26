package frogermcs.io.githubclient;

import frogermcs.io.githubclient.ui.activity.component.SplashActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.SplashActivityModule;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Miroslaw Stanek on 19.09.15.
 */
public class TestGithubClientApplication
        extends GithubClientApplication {

    private AppComponent appComponent;
    private SplashActivityComponent splashActivityComponent;

    @Override
    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = mock(AppComponent.class);
            when(appComponent.plus(any(SplashActivityModule.class)))
                    .thenReturn(splashActivityComponent);
        }

        return appComponent;
    }

    public void setSplashActivityComponent(SplashActivityComponent component) {
        this.splashActivityComponent = component;
    }
}