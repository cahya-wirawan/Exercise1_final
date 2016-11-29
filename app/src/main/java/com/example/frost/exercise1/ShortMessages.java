package com.example.frost.exercise1;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frost on 19.11.16.
 */

public class ShortMessages {
    @SerializedName("messages")
    private List<ShortMessageModel> messages = new ArrayList<>();

    public ShortMessages() {
        //required for GSON
    }

    //region Getters and Setters

    public List<ShortMessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<ShortMessageModel> messages) {
        this.messages = messages;
    }
    //endregion
}
