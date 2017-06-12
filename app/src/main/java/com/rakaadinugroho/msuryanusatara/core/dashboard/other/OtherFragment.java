package com.rakaadinugroho.msuryanusatara.core.dashboard.other;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.core.authentication.AuthActivity;
import com.rakaadinugroho.msuryanusatara.utils.SessionManagement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherFragment extends Fragment implements OtherView {
    private OtherPresenter presenter;
    public OtherFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_other, container, false);
        ButterKnife.bind(this, view);
        initComponent(view);
        return view;
    }

    private void initComponent(View view) {
        presenter   = new OtherPresenter(this);
    }

    @OnClick(R.id.other_signout)
    public void signoutProcess(){
        presenter.signoutSystem();
    }
    @OnClick(R.id.other_about)
    public void aboutUs(){
        Toast.makeText(getActivity(), "Develop by Raka Adi Nugroho", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.other_helpme)
    public void helpMe(){
        Uri uri = Uri.parse("http://www.suryanusa.esy.es");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    @Override
    public void signout() {
        Intent intent   = new Intent(getActivity(), AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }
}
