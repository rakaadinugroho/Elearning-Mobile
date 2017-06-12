package com.rakaadinugroho.msuryanusatara.core.authentication.signin;

import com.rakaadinugroho.msuryanusatara.models.ResponseAuth;

/**
 * Created by Raka Adi Nugroho on 5/10/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public interface SignInView {
    void showLoading(String loadingMessages);
    void hideLoading();
    void dataFailed(String message);
    void dataSuccess(ResponseAuth responseAuth);
}
