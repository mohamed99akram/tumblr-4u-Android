
package com.example.tumblr4u.ApiData.RetrieveNotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotesResponse {

    @SerializedName("res")
    @Expose
    private Res res;

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

}
