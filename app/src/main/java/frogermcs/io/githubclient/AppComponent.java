package frogermcs.io.githubclient;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
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
                AppModule.class
        }
)
public interface AppComponent {

    SplashActivityComponent plus(SplashActivityModule module);

    UserComponent plus(UserModule userModule);

    Application application();
}