
package com.rakaadinugroho.msuryanusatara.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ResponsePassedExam {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("pass_exam")
    private List<PassExam> mPassExam;
    @SerializedName("status")
    private Boolean mStatus;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<PassExam> getPassExam() {
        return mPassExam;
    }

    public void setPassExam(List<PassExam> passExam) {
        mPassExam = passExam;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
