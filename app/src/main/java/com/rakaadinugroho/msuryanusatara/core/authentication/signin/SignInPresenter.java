package com.rakaadinugroho.msuryanusatara.core.authentication.signin;
import com.rakaadinugroho.msuryanusatara.models.ResponseAuth;
import com.rakaadinugroho.msuryanusatara.network.NetworkClient;
import com.rakaadinugroho.msuryanusatara.network.RequestAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Raka Adi Nugroho on 5/10/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class SignInPresenter{
    private SignInView view;
    private CompositeDisposable mCompositeDisposable;

    public SignInPresenter(SignInView view){
        this.view   = view;
    }

    public void loginProcess(String username, String password){
        view.showLoading("Pleasewait...");
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);
        if (mCompositeDisposable == null)
            mCompositeDisposable    = new CompositeDisposable();

        mCompositeDisposable.add(requestAPI.login(username, password)
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
