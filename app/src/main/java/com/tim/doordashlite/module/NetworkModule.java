package com.tim.doordashlite.module;

import com.tim.doordashlite.network.DoorDashService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import toothpick.config.Module;

/**
 * Created by Home on 5/31/17.
 */

public class NetworkModule extends Module {

    private static final String BASE_URL = "https://api.doordash.com/";

    public NetworkModule() {
        setupNetwork();
    }

    public void setupNetwork() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        bind(OkHttpClient.class).toInstance(okHttpClient);
        bind(Retrofit.class).toInstance(retrofit);
        bind(DoorDashService.class).toInstance(retrofit.create(DoorDashService.class));
    }
}
