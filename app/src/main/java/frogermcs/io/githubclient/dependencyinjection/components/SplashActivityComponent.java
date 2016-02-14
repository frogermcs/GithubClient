package frogermcs.io.githubclient.dependencyinjection.components;

import dagger.Subcomponent;
import frogermcs.io.githubclient.dependencyinjection.scopes.ActivityScope;
import frogermcs.io.githubclient.ui.activity.SplashActivity;
import frogermcs.io.githubclient.dependencyinjection.modules.SplashActivityModule;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = SplashActivityModule.class
)
public interface SplashActivityComponent {

    SplashActivity inject(SplashActivity splashActivity);

}