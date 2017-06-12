package com.rakaadinugroho.msuryanusatara.network;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rakaadinugroho.msuryanusatara.BaseApps;
import com.rakaadinugroho.msuryanusatara.core.Const;
import com.rakaadinugroho.msuryanusatara.utils.SessionManagement;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raka Adi Nugroho on 5/11/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class NetworkClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        if (retrofit == null){
            Gson gson   = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient.Builder builder    = new OkHttpClient.Builder();
            OkHttpClient okHttpClient   = builder.build();
            retrofit    = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
