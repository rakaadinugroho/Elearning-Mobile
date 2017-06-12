
package com.rakaadinugroho.msuryanusatara.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ResponseCategory {

    @SerializedName("category")
    private List<Category> mCategory;
    @SerializedName("status")
    private Boolean mStatus;

    public List<Category> getCategory() {
        return mCategory;
    }

    public void setCategory(List<Category> category) {
        mCategory = category;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
