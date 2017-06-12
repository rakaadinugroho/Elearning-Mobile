
package com.rakaadinugroho.msuryanusatara.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Auth {

    @SerializedName("fullname")
    private String mFullname;
    @SerializedName("token")
    private String mToken;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("userid")
    private Integer userid;
    @SerializedName("userkon")
    private Integer userkon;
    public Integer getUserkon(){
        return userkon;
    }
    public void setUserkon(Integer userkon){
        this.userkon    = userkon;
    }
    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this.userid = userid;
    }
    public String getFullname() {
        return mFullname;
    }

    public void setFullname(String fullname) {
        mFullname = fullname;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
