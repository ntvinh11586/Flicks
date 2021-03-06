package com.coderschool.vinh.flicks.utils;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static Retrofit getMovie(String apiKey) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client(apiKey))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getTrailer(String apiKey) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client(apiKey))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient client(String apiKey) {
        return new OkHttpClient.Builder()
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(apiKeyInterceptor(apiKey))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    // This method of Interceptor call when we call api
    private static Interceptor apiKeyInterceptor(final String apiKey) {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                // Build Url for request
                Request request = chain.request();
                HttpUrl url = request.url()
                        .newBuilder()
                        .addQueryParameter("api_key", apiKey)
                        .build();
                // Build request
                request = request.newBuilder()
                        .url(url)
                        .build();
                return chain.proceed(request);
            }
        };
    }
}
