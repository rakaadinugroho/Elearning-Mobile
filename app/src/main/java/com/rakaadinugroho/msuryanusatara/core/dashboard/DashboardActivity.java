package com.rakaadinugroho.msuryanusatara.core.dashboard;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.adapter.PageAdapter;
import com.rakaadinugroho.msuryanusatara.core.dashboard.account.AccountFragment;
import com.rakaadinugroho.msuryanusatara.core.dashboard.home.HomeFragment;
import com.rakaadinugroho.msuryanusatara.core.dashboard.other.OtherFragment;
import com.rakaadinugroho.msuryanusatara.core.dashboard.transactional.TransactionalFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    private int[] tab_icons = new int[]{
            R.drawable.ic_home,
            R.drawable.ic_shopping,
            R.drawable.ic_account,
            R.drawable.ic_more
    };

    @BindView(R.id.dashboard_toolbartitle)
    TextView dashboard_toolbartitle;

    @BindView(R.id.dashboard_toolbar)
    Toolbar dashboard_toolbar;

    @BindView(R.id.dashboard_pager)
    ViewPager dashboard_pager;

    @BindView(R.id.dashboard_tablayout)
    TabLayout dashboard_tablayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        if (dashboard_toolbar != null){
            setSupportActionBar(dashboard_toolbar);
            createViewPager(dashboard_pager);
            dashboard_tablayout.setupWithViewPager(dashboard_pager);
            createTabIcon();
        }
    }

    private void createTabIcon() {
        dashboard_tablayout.getTabAt(0).setIcon(tab_icons[0]);
        dashboard_tablayout.getTabAt(1).setIcon(tab_icons[1]);
        dashboard_tablayout.getTabAt(2).setIcon(tab_icons[2]);
        dashboard_tablayout.getTabAt(3).setIcon(tab_icons[3]);
    }

    private void createViewPager(ViewPager viewPager){
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), getApplicationContext());
        pageAdapter.addFragment(new HomeFragment(), getResources().getString(R.string.tab_home));
        pageAdapter.addFragment(new TransactionalFragment(), getResources().getString(R.string.tab_transaction));
        pageAdapter.addFragment(new AccountFragment(), getResources().getString(R.string.tab_account));
        pageAdapter.addFragment(new OtherFragment(), getResources().getString(R.string.tab_setting));

        viewPager.setAdapter(pageAdapter);
    }
}
