package frogermcs.io.githubclient.dependencyinjection.modules;

import dagger.Module;
import dagger.Provides;
import frogermcs.io.githubclient.dependencyinjection.scopes.UserScope;
import frogermcs.io.githubclient.data.api.GithubApiService;
import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.data.model.User;

/**
 * Created by Miroslaw Stanek on 23.06.15.
 */
@Module
public class UserModule {

    private User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }

    @Provides
    @UserScope
    RepositoriesManager provideRepositoriesManager(User user, GithubApiService githubApiService) {
        return new RepositoriesManager(user, githubApiService);
    }
}
