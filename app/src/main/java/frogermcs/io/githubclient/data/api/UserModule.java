package frogermcs.io.githubclient.data.api;

import dagger.Module;
import dagger.Provides;
import frogermcs.io.githubclient.data.UserScope;
import frogermcs.io.githubclient.data.model.User;

/**
 * Created by Miroslaw Stanek on 23.06.15.
 */
@Module
public class UserModule {

    private User user;
    private GithubApiService githubApiService;

    private UserModule(User user, GithubApiService githubApiService) {
        this.user = user;
        this.githubApiService = githubApiService;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }

    @Provides
    @UserScope
    RepositoriesManager provideRepositoriesManager() {
        return new RepositoriesManager(user, githubApiService);
    }

    public static class Factory {
        private GithubApiService githubApiService;

        public Factory(GithubApiService githubApiService) {
            this.githubApiService = githubApiService;
        }

        public UserModule create(User user) {
            return new UserModule(user, githubApiService);
        }
    }
}
