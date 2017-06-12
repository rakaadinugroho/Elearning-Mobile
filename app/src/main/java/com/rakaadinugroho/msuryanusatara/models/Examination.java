
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Examination {

    @SerializedName("answer")
    private Answer mAnswer;
    @SerializedName("question")
    private String mQuestion;
    @SerializedName("question_correct")
    private String mQuestionCorrect;
    @SerializedName("question_id")
    private String mQuestionId;
    @SerializedName("question_weight")
    private String mQuestionWeight;

    public Answer getAnswer() {
        return mAnswer;
    }

    public void setAnswer(Answer answer) {
        mAnswer = answer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getQuestionCorrect() {
        return mQuestionCorrect;
    }

    public void setQuestionCorrect(String questionCorrect) {
        mQuestionCorrect = questionCorrect;
    }

    public String getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(String questionId) {
        mQuestionId = questionId;
    }

    public String getQuestionWeight() {
        return mQuestionWeight;
    }

    public void setQuestionWeight(String questionWeight) {
        mQuestionWeight = questionWeight;
    }

}
