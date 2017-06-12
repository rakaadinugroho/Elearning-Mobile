
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Answer {

    @SerializedName("A")
    private String mA;
    @SerializedName("B")
    private String mB;
    @SerializedName("C")
    private String mC;
    @SerializedName("D")
    private String mD;

    public String getA() {
        return mA;
    }

    public void setA(String A) {
        mA = A;
    }

    public String getB() {
        return mB;
    }

    public void setB(String B) {
        mB = B;
    }

    public String getC() {
        return mC;
    }

    public void setC(String C) {
        mC = C;
    }

    public String getD() {
        return mD;
    }

    public void setD(String D) {
        mD = D;
    }

}
