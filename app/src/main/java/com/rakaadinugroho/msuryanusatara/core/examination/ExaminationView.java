package com.rakaadinugroho.msuryanusatara.core.examination;

import com.rakaadinugroho.msuryanusatara.models.ResponsePackage;
import com.rakaadinugroho.msuryanusatara.models.ResponsePassedExam;

/**
 * Created by Raka Adi Nugroho on 5/29/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public interface ExaminationView {
    void showLoading();
    void hideLoading();
    void dataExamination(ResponsePackage responsePackage);
    void dataExaminationFailed(String message);
    void dataPassedExamination(ResponsePassedExam responsePassedExam);
    void dataPassedExaminationFailed(String message);
}
