package com.rakaadinugroho.msuryanusatara.core.dashboard.home;

import android.content.Intent;

import com.rakaadinugroho.msuryanusatara.models.ResponseCategory;
import com.rakaadinugroho.msuryanusatara.models.ResponseLatest;

/**
 * Created by Raka Adi Nugroho on 5/19/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public interface HomeView {
    void showLoading();
    void hideLoading();
    void categorySuccess(ResponseCategory responseCategory);
    void categoryFailed(String failed);
    void latestSuccess(ResponseLatest latest);
    void latestFailed(String failed);
    void moveToCategory(Intent intent);
    void moveToPackage(Intent intent);
}
