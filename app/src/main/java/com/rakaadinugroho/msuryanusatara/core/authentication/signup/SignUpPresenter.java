package com.rakaadinugroho.msuryanusatara.core.authentication.signup;

import com.rakaadinugroho.msuryanusatara.models.ResponseAuth;
import com.rakaadinugroho.msuryanusatara.network.NetworkClient;
import com.rakaadinugroho.msuryanusatara.network.RequestAPI;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Raka Adi Nugroho on 5/12/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class SignUpPresenter {
    private SignUpView view;
    private CompositeDisposable mCompositeDisposable;
    public SignUpPresenter(SignUpView view) {
        this.view = view;
    }

    public void registerProcess(String fulname, String username, String password, String level){
        view.showLoading("Register processing...");
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);
        if (mCompositeDisposable == null)
            mCompositeDisposable    = new CompositeDisposable();

        mCompositeDisposable.add(requestAPI.register(username, password, fulname, level)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        responseAuth -> handleResponse(responseAuth),
                        throwable -> handleError(throwable)
                )
        );
    }
    private void handleResponse(ResponseAuth responseAuth){
        view.dataSuccess(responseAuth);
        view.hideLoading();
    }

    private void handleError(Throwable throwable){
        view.dataFailed(throwable.getLocalizedMessage().toString());
        view.hideLoading();
    }
    public void destroyData(){
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
    }
}
