
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Hasil {

    @SerializedName("jawab")
    private String mJawab;
    @SerializedName("soal_id")
    private String mSoalId;

    public String getJawab() {
        return mJawab;
    }

    public void setJawab(String jawab) {
        mJawab = jawab;
    }

    public String getSoalId() {
        return mSoalId;
    }

    public void setSoalId(String soalId) {
        mSoalId = soalId;
    }

}
