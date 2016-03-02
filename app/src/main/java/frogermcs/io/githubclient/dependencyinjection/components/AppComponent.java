package frogermcs.io.githubclient.dependencyinjection.components;

import javax.inject.Singleton;

import dagger.Component;
import frogermcs.io.githubclient.dependencyinjection.modules.AppModule;
import frogermcs.io.githubclient.data.api.GithubApiModule;
import frogermcs.io.githubclient.dependencyinjection.modules.UserModule;
import frogermcs.io.githubclient.dependencyinjection.modules.SplashActivityModule;

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