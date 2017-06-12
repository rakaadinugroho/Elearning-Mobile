package com.rakaadinugroho.msuryanusatara.core.authentication.signup;

import com.rakaadinugroho.msuryanusatara.models.ResponseAuth;

/**
 * Created by Raka Adi Nugroho on 5/12/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public interface SignUpView {
    void showLoading(String loadingMessages);
    void hideLoading();
    void dataFailed(String message);
    void dataSuccess(ResponseAuth responseAuth);
}
