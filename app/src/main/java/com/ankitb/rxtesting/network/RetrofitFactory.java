package com.ankitb.rxtesting.network;

import com.ankitb.rxtesting.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ankit on 06/08/16.
 */
public class RetrofitFactory {

    public static Retrofit getAdapter(){
        return createRetrofit();
    }

    private static Retrofit createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(getGsonConvertorFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getHttpClient()).build();
        return retrofit;

    }

    private static GsonConverterFactory getGsonConvertorFactory(){
        return GsonConverterFactory.create();
    }

    private static OkHttpClient getHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG){
            builder.addInterceptor(getLoggingInterceptor());
        }
        return builder.build();
    }

    private static HttpLoggingInterceptor getLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
