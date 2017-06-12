package com.rakaadinugroho.msuryanusatara.core.authentication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.core.authentication.signin.SignInFragment;

import butterknife.ButterKnife;

public class AuthActivity extends AppCompatActivity implements AuthView {
    private AuthPresenter authPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

        authPresenter   = new AuthPresenter(this);

        final SignInFragment signInFragment = new SignInFragment();
        loadFragmentSignIn(signInFragment);

    }
    @Override
    public void moveTo(Intent intent) {
        startActivity(intent);
    }

    private void loadFragmentSignIn(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_auth, fragment, "Load SignIn")
                .commit();
    }
}
