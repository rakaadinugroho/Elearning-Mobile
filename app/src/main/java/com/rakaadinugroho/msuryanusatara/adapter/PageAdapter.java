package com.rakaadinugroho.msuryanusatara.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.core.dashboard.account.AccountFragment;
import com.rakaadinugroho.msuryanusatara.core.dashboard.home.HomeFragment;
import com.rakaadinugroho.msuryanusatara.core.dashboard.other.OtherFragment;
import com.rakaadinugroho.msuryanusatara.core.dashboard.transactional.TransactionalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raka Adi Nugroho on 5/13/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class PageAdapter extends FragmentPagerAdapter{
    private final List<Fragment> mFragmentList  = new ArrayList<>();
    private final List<String> mFragmentTitle   = new ArrayList<>();
    Context context;

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context    = context;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitle.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }
}
