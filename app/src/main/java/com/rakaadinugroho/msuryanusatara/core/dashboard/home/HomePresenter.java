package com.rakaadinugroho.msuryanusatara.core.dashboard.home;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.rakaadinugroho.msuryanusatara.BaseApps;
import com.rakaadinugroho.msuryanusatara.core.category.CategoryActivity;
import com.rakaadinugroho.msuryanusatara.core.examination.ExaminationActivity;
import com.rakaadinugroho.msuryanusatara.models.Category;
import com.rakaadinugroho.msuryanusatara.models.PackageLatest;
import com.rakaadinugroho.msuryanusatara.models.ResponseCategory;
import com.rakaadinugroho.msuryanusatara.models.ResponseLatest;
import com.rakaadinugroho.msuryanusatara.network.NetworkClient;
import com.rakaadinugroho.msuryanusatara.network.RequestAPI;
import com.rakaadinugroho.msuryanusatara.utils.SessionManagement;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Raka Adi Nugroho on 5/19/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class HomePresenter {
    private HomeView view;
    private CompositeDisposable mCompositeDisposable;
    private SessionManagement mSessionManagement;
    private HashMap<String, String> user;

    public HomePresenter(HomeView view){
        this.view   = view;
        this.mSessionManagement = new SessionManagement(BaseApps.getAppContext());
    }
    /*
    Load data with Zip Rxjava Combined 2 endPoint
     */
    public void loadAllData(){
        view.showLoading();
        loadToken();
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        Observable<CategoryAndLatest> combineData   = Observable.zip(
                loadCategoryData(),
                loadLatestData(),
                new BiFunction<ResponseCategory, ResponseLatest, CategoryAndLatest>() {
                    @Override
                    public CategoryAndLatest apply(@NonNull ResponseCategory responseCategory,
                                                   @NonNull ResponseLatest latest) throws Exception {
                        return new CategoryAndLatest(responseCategory, latest);
                    }
                }
        );
        mCompositeDisposable.add(
                combineData.subscribe(categoryAndLatest -> combineSuccessObserver(categoryAndLatest))
        );
    }
    // Combine Observer
    private void combineSuccessObserver(CategoryAndLatest categoryAndLatest){
        Log.d(TAG, "combineSuccessObserver: "+ categoryAndLatest.toString());
        handleCategory(categoryAndLatest.category);
        handleLatest(categoryAndLatest.latest);
    }
    private void combineErrorObserver(Throwable throwable){
        handleCategoryError(throwable);
        handleLatestError(throwable);
    }
    // Get Response Observable from Category
    private Observable<ResponseCategory> loadCategoryData(){
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);

        return requestAPI.category(user.get(SessionManagement.key_token).toString())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponseCategory>>() {
                    @Override
                    public ObservableSource<? extends ResponseCategory> apply(@NonNull Throwable throwable) throws Exception {
                        handleCategoryError(throwable);
                        return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    // Get Response Observable from Latest Package
    private Observable<ResponseLatest> loadLatestData(){
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);

        return requestAPI.latestpackage(user.get(SessionManagement.key_token).toString())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponseLatest>>() {
                    @Override
                    public ObservableSource<? extends ResponseLatest> apply(@NonNull Throwable throwable) throws Exception {
                        handleLatestError(throwable);
                        return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    // Get Available Token
    private void loadToken() {
        if (mSessionManagement != null){
            user = mSessionManagement.getUserDetails();
            Log.d(TAG, "loadToken: "+ user.get(SessionManagement.key_userid).toString());
            Log.d(TAG, "loadToken: "+ user.get(SessionManagement.key_userkon).toString());
            Log.d(TAG, "loadToken: "+ user.get(SessionManagement.key_userid).toString());
        }
    }
    /*
    Handle Category Response
     */
    private void handleCategory(ResponseCategory category){
        view.categorySuccess(category);
        view.hideLoading();
    }
    private void handleCategoryError(Throwable throwable){
        view.categoryFailed(throwable.getLocalizedMessage().toString());
        view.hideLoading();
    }
    /*
    Handle Latest Response
     */
    private void handleLatest(ResponseLatest latest){
        view.latestSuccess(latest);
    }
    private void handleLatestError(Throwable throwable){
        view.latestFailed(throwable.getLocalizedMessage().toString());
    }
    /* Handle Moving Data */
    public void getItemCategory(Category category, Activity activity){
        Intent intent   = new Intent(activity, CategoryActivity.class);
        intent.putExtra("title", category.getCategoryName());
        intent.putExtra("subtitle", category.getCategoryDetail().toString());
        intent.putExtra("id", category.getCategoryId());
        view.moveToCategory(intent);
    }
    public void getItemPackage(PackageLatest latest, Activity activity){
        Intent intent   = new Intent(activity, ExaminationActivity.class);
        intent.putExtra("id", latest.getExamId());
        intent.putExtra("title", latest.getExamTitle());
        view.moveToPackage(intent);
    }
    /* On Stop Request Composite Disposable */
    public void destroyData(){
        if (mCompositeDisposable != null)
            mCompositeDisposable.dispose();
    }

    class CategoryAndLatest{
        public ResponseCategory category;
        public ResponseLatest latest;

        public CategoryAndLatest(ResponseCategory category, ResponseLatest latest) {
            this.category = category;
            this.latest = latest;
        }
    }
}
