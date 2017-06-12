
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RequestAnswer {

    @SerializedName("jawaban")
    private String mJawaban;
    @SerializedName("soal_id")
    private String mSoalId;

    public String getJawaban() {
        return mJawaban;
    }

    public void setJawaban(String jawaban) {
        mJawaban = jawaban;
    }

    public String getSoalId() {
        return mSoalId;
    }

    public void setSoalId(String soalId) {
        mSoalId = soalId;
    }

}
