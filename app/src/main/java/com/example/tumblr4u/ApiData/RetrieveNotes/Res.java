
package com.example.tumblr4u.ApiData.RetrieveNotes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res {

    @SerializedName("messege")
    @Expose
    private String messege;
    @SerializedName("notes")
    @Expose
    private List<Note> notes = null;

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

}
