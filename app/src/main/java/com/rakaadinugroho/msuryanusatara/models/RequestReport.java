
package com.rakaadinugroho.msuryanusatara.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RequestReport {

    @SerializedName("exam_id")
    private String mExamId;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("hasil")
    private List<Hasil> mHasil;

    public String getExamId() {
        return mExamId;
    }

    public void setExamId(String examId) {
        mExamId = examId;
    }

    public List<Hasil> getHasil() {
        return mHasil;
    }

    public void setHasil(List<Hasil> hasil) {
        mHasil = hasil;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
