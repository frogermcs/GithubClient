package frogermcs.io.githubclient;

import javax.inject.Singleton;

import dagger.Component;
import frogermcs.io.githubclient.data.api.GithubApiModule;
import frogermcs.io.githubclient.data.UserComponent;
import frogermcs.io.githubclient.data.api.UserModule;
import frogermcs.io.githubclient.ui.activity.component.SplashActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.SplashActivityModule;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                GithubApiModule.class
        }
)
public interface AppComponent {

    SplashActivityComponent plus(SplashActivityModule module);

    UserComponent plus(UserModule userModule);

}