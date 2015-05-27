package frogermcs.io.githubclient.data.api;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import frogermcs.io.githubclient.BuildConfig;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.ApiScope;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Module
public class GithubApiModule {

    @Provides
    @ApiScope
    OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }

    @Provides
    @ApiScope
    RestAdapter provideRestAdapter(Application application, OkHttpClient okHttpClient) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setClient(new OkClient(okHttpClient))
                .setEndpoint(application.getString(R.string.endpoint));

        if (BuildConfig.DEBUG) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
        }

        return builder.build();
    }

    @Provides
    @ApiScope
    GithubApiService provideGithubApiService(RestAdapter restAdapter) {
        return restAdapter.create(GithubApiService.class);
    }

    @Provides
    @ApiScope
    UserManager provideUserManager(GithubApiService githubApiService) {
        return new UserManager(githubApiService);
    }

    @Provides
    @ApiScope
    RepositoriesManager provideRepositoriesManager(GithubApiService githubApiService) {
        return new RepositoriesManager(githubApiService);
    }
}
