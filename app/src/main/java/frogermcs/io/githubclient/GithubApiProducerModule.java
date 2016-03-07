package frogermcs.io.githubclient;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;
import frogermcs.io.githubclient.data.api.GithubApiService;
import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.data.api.UserModule;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Miroslaw Stanek on 05.03.2016.
 */
@ProducerModule
public class GithubApiProducerModule {

    @Produces
    public OkHttpClient produceOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

        return builder.build();
    }

    @Produces
    public Retrofit produceRestAdapter(Application application, OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(application.getString(R.string.endpoint))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    @Produces
    public GithubApiService produceGithubApiService(Retrofit restAdapter) {
        return restAdapter.create(GithubApiService.class);
    }

    @Produces
    public UserManager produceUserManager(GithubApiService githubApiService) {
        return new UserManager(githubApiService);
    }

    @Produces
    public UserModule.Factory produceUserModuleFactory(GithubApiService githubApiService) {
        return new UserModule.Factory(githubApiService);
    }
}
