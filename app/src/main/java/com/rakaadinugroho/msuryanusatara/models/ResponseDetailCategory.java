
package com.rakaadinugroho.msuryanusatara.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ResponseDetailCategory {

    @SerializedName("category_detail")
    private String mCategoryDetail;
    @SerializedName("category_name")
    private String mCategoryName;
    @SerializedName("category_thumbnail")
    private String mCategoryThumbnail;
    @SerializedName("list_package")
    private List<ListPackage> mListPackage;
    @SerializedName("status")
    private Boolean mStatus;

    public String getCategoryDetail() {
        return mCategoryDetail;
    }

    public void setCategoryDetail(String categoryDetail) {
        mCategoryDetail = categoryDetail;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCategoryThumbnail() {
        return mCategoryThumbnail;
    }

    public void setCategoryThumbnail(String categoryThumbnail) {
        mCategoryThumbnail = categoryThumbnail;
    }

    public List<ListPackage> getListPackage() {
        return mListPackage;
    }

    public void setListPackage(List<ListPackage> listPackage) {
        mListPackage = listPackage;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
