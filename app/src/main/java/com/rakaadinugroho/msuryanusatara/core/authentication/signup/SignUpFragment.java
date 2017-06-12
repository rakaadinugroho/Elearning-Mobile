package com.rakaadinugroho.msuryanusatara.core.authentication.signup;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.core.authentication.signin.SignInFragment;
import com.rakaadinugroho.msuryanusatara.models.ResponseAuth;
import com.rakaadinugroho.msuryanusatara.utils.ActivityUtils;
import com.rakaadinugroho.msuryanusatara.utils.SessionManagement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment implements SignUpView{
    private static final String TAG = SignUpFragment.class.getSimpleName();
    private SignUpPresenter signUpPresenter;
    private ProgressDialog loading;
    private SessionManagement sessionManagement;

    @BindView(R.id.signup_level)
    Spinner signup_level;

    @BindView(R.id.signup_anchor_signin)
    TextView signup_anchor_signin;

    @BindView(R.id.signup_username)
    TextInputEditText signup_username;

    @BindView(R.id.signup_button)
    AppCompatButton signup_button;

    @BindView(R.id.signup_fullname)
    TextInputEditText signup_fullname;

    @BindView(R.id.signup_password)
    TextInputEditText signup_password;

    public SignUpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        initComponent(view);
        return view;
    }

    private void initComponent(View view) {
        /*
        Spinner Level
         */
        List<String> levelitems    = new ArrayList<>();
        levelitems.add("SD Sederajat");
        levelitems.add("SMP Sederajat");
        levelitems.add("SMA Sederajat");
        levelitems.add("S1/D Sederajat");
        levelitems.add("Umum");
        ArrayAdapter<String> leveladapter   = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, levelitems);
        leveladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        signup_level.setAdapter(leveladapter);

        signUpPresenter = new SignUpPresenter(this);
        loading = new ProgressDialog(getActivity());
        sessionManagement   = new SessionManagement(getContext());

        sessionManagement.checkAuth();
    }

    @OnClick(R.id.signup_anchor_signin)
    public void anchorSignIn(){
        ActivityUtils.goToFragment(getFragmentManager(), new SignInFragment(), R.id.frame_auth);
    }

    @OnClick(R.id.signup_button)
    public void registerNow(){
        String fullname = signup_fullname.getText().toString();
        String username = signup_username.getText().toString();
        String password = signup_password.getText().toString();
        String level    = signup_level.getSelectedItem().toString();
        if (TextUtils.isEmpty(fullname) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(level)){
            Toast.makeText(getActivity(), "Input Spesific Data", Toast.LENGTH_SHORT).show();
            return;
        }
        signUpPresenter.registerProcess(fullname, username, password, level);
    }

    @Override
    public void showLoading(String loadingMessages) {
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        loading.setMessage(loadingMessages);

        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.dismiss();
    }

    @Override
    public void dataFailed(String message) {
        Toast.makeText(getActivity(), "Error : "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataSuccess(ResponseAuth responseAuth) {
        if (responseAuth.getStatus()) {
            Toast.makeText(getActivity(), responseAuth.getMessage().toString(), Toast.LENGTH_SHORT).show();
        } else{
            this.dataFailed(responseAuth.getMessage().toString());
        }

        sessionManagement.checkAuth();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        signUpPresenter.destroyData();
    }
}
