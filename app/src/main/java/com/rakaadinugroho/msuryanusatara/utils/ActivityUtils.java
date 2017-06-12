package com.rakaadinugroho.msuryanusatara.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Raka Adi Nugroho on 5/12/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class ActivityUtils {
    public static void goToFragment(FragmentManager fragmentManager, Fragment fragment, int framelayout){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(framelayout, fragment, fragment.getTag());
        fragmentTransaction.commit();
    }
}
