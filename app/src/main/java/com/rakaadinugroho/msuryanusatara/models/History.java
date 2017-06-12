
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class History {

    @SerializedName("exam_correct")
    private String mExamCorrect;
    @SerializedName("exam_date")
    private String mExamDate;
    @SerializedName("exam_title")
    private String mExamTitle;
    @SerializedName("exam_weight")
    private String mExamWeight;

    public String getExamCorrect() {
        return mExamCorrect;
    }

    public void setExamCorrect(String examCorrect) {
        mExamCorrect = examCorrect;
    }

    public String getExamDate() {
        return mExamDate;
    }

    public void setExamDate(String examDate) {
        mExamDate = examDate;
    }

    public String getExamTitle() {
        return mExamTitle;
    }

    public void setExamTitle(String examTitle) {
        mExamTitle = examTitle;
    }

    public String getExamWeight() {
        return mExamWeight;
    }

    public void setExamWeight(String examWeight) {
        mExamWeight = examWeight;
    }

}
