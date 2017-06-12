
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PassExam {

    @SerializedName("correct")
    private String mCorrect;
    @SerializedName("exam_end")
    private String mExamEnd;
    @SerializedName("exam_start")
    private String mExamStart;
    @SerializedName("exam_status")
    private String mExamStatus;
    @SerializedName("exam_value")
    private String mExamValue;
    @SerializedName("exam_weight")
    private String mExamWeight;
    @SerializedName("list_answer")
    private String mListAnswer;
    @SerializedName("list_exam")
    private String mListExam;

    public String getCorrect() {
        return mCorrect;
    }

    public void setCorrect(String correct) {
        mCorrect = correct;
    }

    public String getExamEnd() {
        return mExamEnd;
    }

    public void setExamEnd(String examEnd) {
        mExamEnd = examEnd;
    }

    public String getExamStart() {
        return mExamStart;
    }

    public void setExamStart(String examStart) {
        mExamStart = examStart;
    }

    public String getExamStatus() {
        return mExamStatus;
    }

    public void setExamStatus(String examStatus) {
        mExamStatus = examStatus;
    }

    public String getExamValue() {
        return mExamValue;
    }

    public void setExamValue(String examValue) {
        mExamValue = examValue;
    }

    public String getExamWeight() {
        return mExamWeight;
    }

    public void setExamWeight(String examWeight) {
        mExamWeight = examWeight;
    }

    public String getListAnswer() {
        return mListAnswer;
    }

    public void setListAnswer(String listAnswer) {
        mListAnswer = listAnswer;
    }

    public String getListExam() {
        return mListExam;
    }

    public void setListExam(String listExam) {
        mListExam = listExam;
    }

}
