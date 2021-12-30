
package com.example.tumblr4u.ApiData.AddComment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res {

    @SerializedName("messege")
    @Expose
    private String messege;
    @SerializedName("commend Id")
    @Expose
    private String commendId;

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public String getCommendId() {
        return commendId;
    }

    public void setCommendId(String commendId) {
        this.commendId = commendId;
    }

}
