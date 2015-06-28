package frogermcs.io.githubclient.data;

import javax.inject.Singleton;

import dagger.Subcomponent;
import frogermcs.io.githubclient.data.api.GithubApiModule;
import frogermcs.io.githubclient.data.api.UserComponent;
import frogermcs.io.githubclient.data.api.UserModule;
import frogermcs.io.githubclient.ui.activity.component.SplashActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.SplashActivityModule;

/**
 * Created by Miroslaw Stanek on 28.06.15.
 */
@Singleton
@Subcomponent(
        modules = {
                GithubApiModule.class
        }
)
public interface DataComponent {

    UserComponent plus(UserModule userModule);

    SplashActivityComponent plus(SplashActivityModule splashActivityModule);
}
