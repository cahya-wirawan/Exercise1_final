package com.example.frost.exercise1;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frost on 19.11.16.
 */

public class ShortMessageModel {
    @SerializedName("created")
    private String created;

    @SerializedName("text")
    private String text;

    public ShortMessageModel() {
        //Required for GSON
    }

    //region Getters and Setters

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
