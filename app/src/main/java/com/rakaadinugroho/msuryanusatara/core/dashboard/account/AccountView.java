package com.rakaadinugroho.msuryanusatara.core.dashboard.account;

import com.rakaadinugroho.msuryanusatara.models.ResponseAuth;

import java.util.HashMap;

/**
 * Created by Raka Adi Nugroho on 6/5/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public interface AccountView {
    void loadDetail(HashMap<String, String> user);
    void passwordChangged(ResponseAuth responseAuth);
    void passwordChanggedError(String message);
}
