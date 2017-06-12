package com.rakaadinugroho.msuryanusatara.core.dashboard.other;

import com.rakaadinugroho.msuryanusatara.BaseApps;
import com.rakaadinugroho.msuryanusatara.utils.SessionManagement;

/**
 * Created by Raka Adi Nugroho on 6/5/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class OtherPresenter {
    OtherView otherView;
    private SessionManagement mSessionManagement;
    public OtherPresenter(OtherView otherView) {
        this.otherView = otherView;
        mSessionManagement  = new SessionManagement(BaseApps.getAppContext());
    }

    public void signoutSystem(){
        mSessionManagement.logout();
        otherView.signout();
    }
}
