package frogermcs.io.githubclient.data.api;

import java.util.List;

import frogermcs.io.githubclient.data.api.response.RepositoryResponse;
import frogermcs.io.githubclient.data.api.response.UserResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public interface GithubApiService {

    @GET("/users/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<RepositoryResponse>> getUsersRepositories(
            @Path("username") String username
    );
}
