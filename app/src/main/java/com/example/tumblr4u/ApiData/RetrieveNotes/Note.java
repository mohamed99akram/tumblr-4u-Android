
package com.example.tumblr4u.ApiData.RetrieveNotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("note")
    @Expose
    private Note__1 note;

    public Note__1 getNote() {
        return note;
    }

    public void setNote(Note__1 note) {
        this.note = note;
    }

}
