
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Examreport {

    @SerializedName("correct")
    private Long mCorrect;
    @SerializedName("grade")
    private Long mGrade;
    @SerializedName("grade_weight")
    private Long mGradeWeight;
    @SerializedName("total_exam")
    private Long mTotalExam;

    public Long getCorrect() {
        return mCorrect;
    }

    public void setCorrect(Long correct) {
        mCorrect = correct;
    }

    public Long getGrade() {
        return mGrade;
    }

    public void setGrade(Long grade) {
        mGrade = grade;
    }

    public Long getGradeWeight() {
        return mGradeWeight;
    }

    public void setGradeWeight(Long gradeWeight) {
        mGradeWeight = gradeWeight;
    }

    public Long getTotalExam() {
        return mTotalExam;
    }

    public void setTotalExam(Long totalExam) {
        mTotalExam = totalExam;
    }

}
