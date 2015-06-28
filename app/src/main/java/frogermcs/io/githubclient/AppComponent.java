package frogermcs.io.githubclient;

import javax.inject.Singleton;

import dagger.Component;
import frogermcs.io.githubclient.data.DataComponent;
import frogermcs.io.githubclient.data.api.GithubApiModule;

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

    DataComponent plus(GithubApiModule githubApiModule);

}
