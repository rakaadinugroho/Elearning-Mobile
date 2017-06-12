package com.rakaadinugroho.msuryanusatara.core.authentication;

import android.app.Activity;
import android.content.Intent;

import com.rakaadinugroho.msuryanusatara.core.dashboard.DashboardActivity;

/**
 * Created by Raka Adi Nugroho on 5/10/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class AuthPresenter {
    private AuthView view;

    public AuthPresenter(AuthView view){
        this.view   = view;
    }

    public void moveTo(Activity activity){
        Intent intent   = new Intent(activity, DashboardActivity.class);
        view.moveTo(intent);
    }
}
