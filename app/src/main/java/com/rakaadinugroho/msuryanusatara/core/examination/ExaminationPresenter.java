package com.rakaadinugroho.msuryanusatara.core.examination;

import android.util.Log;

import com.rakaadinugroho.msuryanusatara.BaseApps;
import com.rakaadinugroho.msuryanusatara.models.ResponsePackage;
import com.rakaadinugroho.msuryanusatara.models.ResponsePassedExam;
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
 * Created by Raka Adi Nugroho on 5/29/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class ExaminationPresenter {
    private ExaminationView view;
    private CompositeDisposable mCompositeDisposable;
    private SessionManagement mSessionManagement;
    private HashMap<String, String> user;

    public ExaminationPresenter(ExaminationView view) {
        this.view = view;
        this.mSessionManagement = new SessionManagement(BaseApps.getAppContext());
        loadToken();
    }

    public void loadAll(String examId){
        view.showLoading();
        if (mCompositeDisposable == null)
            mCompositeDisposable    = new CompositeDisposable();

        Observable<ExamAndPassed> combineData   = Observable.zip(
                loadExamData(examId),
                loadPassedData(examId),
                new BiFunction<ResponsePackage, ResponsePassedExam, ExamAndPassed>() {
                    @Override
                    public ExamAndPassed apply(@NonNull ResponsePackage responsePackage, @NonNull ResponsePassedExam responsePassedExam) throws Exception {
                        return new ExamAndPassed(responsePackage, responsePassedExam);
                    }
                }
        );
        mCompositeDisposable.add(
                combineData.subscribe(examAndPassed -> combineSuccessObserver(examAndPassed))
        );
    }
    // Combine Observer
    private void combineSuccessObserver(ExamAndPassed examAndPassed){
        Log.d(TAG, "combineSuccessObserver: " + examAndPassed.toString());
        handleExam(examAndPassed.responsePackage);
        handlePassedExam(examAndPassed.responsePassedExam);
    }
    // Get Response Observable from Examination
    private Observable<ResponsePackage> loadExamData(String examId){
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);

        return requestAPI.examination(user.get(SessionManagement.key_token).toString(), examId)
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponsePackage>>() {
                    @Override
                    public ObservableSource<? extends ResponsePackage> apply(@NonNull Throwable throwable) throws Exception {
                        handleExamError(throwable);
                        return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    // Get Response Observable From passExamination
    private Observable<ResponsePassedExam> loadPassedData(String examId){
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);

        return requestAPI.passedexam(user.get(SessionManagement.key_token).toString(), user.get(SessionManagement.key_userid).toString(), examId)
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponsePassedExam>>() {
                    @Override
                    public ObservableSource<? extends ResponsePassedExam> apply(@NonNull Throwable throwable) throws Exception {
                        handlePassedExamError(throwable);
                        return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    // Handle Success Exam
    private void handleExam(ResponsePackage responsePackage){
        view.dataExamination(responsePackage);
        view.hideLoading();
    }
    // Handle Error Exam
    private void handleExamError(Throwable t){
        view.dataExaminationFailed(t.getLocalizedMessage().toString());
        view.hideLoading();
    }

    // Handle Success PassedExam
    private void handlePassedExam(ResponsePassedExam responsePassed){
        view.dataPassedExamination(responsePassed);
        view.hideLoading();
    }
    // Handle Error PassedExam
    private void handlePassedExamError(Throwable t){
        view.dataPassedExaminationFailed(t.getLocalizedMessage().toString());
        view.hideLoading();
    }
    //
    // Load Token
    private void loadToken(){
        if (mSessionManagement != null){
            user    = mSessionManagement.getUserDetails();
        }
    }

    // Class Combine
    class ExamAndPassed{
        public ResponsePackage responsePackage;
        public ResponsePassedExam responsePassedExam;

        public ExamAndPassed(ResponsePackage responsePackage, ResponsePassedExam responsePassedExam) {
            this.responsePackage = responsePackage;
            this.responsePassedExam = responsePassedExam;
        }
    }
    // On Destroy Application
    public void destroyData(){
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
    }
}
