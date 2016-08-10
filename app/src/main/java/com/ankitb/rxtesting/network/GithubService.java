package com.ankitb.rxtesting.network;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Ankit on 06/08/16.
 */
public class GithubService {

    IGithubApi githubApi ;

    public GithubService(Retrofit retrofit){
        githubApi = retrofit.create(IGithubApi.class);
    }

    public Observable<Repository> getPublicRepos(String userName){
        return githubApi.getPublicRepos(userName);
    }
}
