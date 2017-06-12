
package com.rakaadinugroho.msuryanusatara.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ResponseHistory {

    @SerializedName("history")
    private List<History> mHistory;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;

    public List<History> getHistory() {
        return mHistory;
    }

    public void setHistory(List<History> history) {
        mHistory = history;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
