package com.rakaadinugroho.msuryanusatara.network;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import retrofit2.HttpException;

/**
 * Created by Raka Adi Nugroho on 5/11/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public abstract class NetworkCallback<M> implements Observer<M> {
    private static final String TAG = NetworkCallback.class.getName();
    public abstract void onSuccess(M model);
    public abstract void onFailure(String message);
    public abstract void onFinish();

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            int code    = httpException.code();
            String message  = httpException.getMessage();
            Log.d(TAG, "onError: "+message);
            Log.d(TAG, "onError: "+code);
            onFailure(message);
        }
    }

    @Override
    public void onNext(@NonNull M model) {
        onSuccess(model);
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
