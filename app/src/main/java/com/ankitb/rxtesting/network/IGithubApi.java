package com.ankitb.rxtesting.network;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Ankit on 06/08/16.
 */
public interface IGithubApi {
    @GET("users/{username}/repos")
    Observable<Repository> getPublicRepos(@Path("username") String userName);
}
