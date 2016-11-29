package com.example.frost.exercise1;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frost on 19.11.16.
 */

public class SendShortMessageModel {
    @SerializedName("consumerKey")
    private String consumerKey;

    @SerializedName("consumerSecret")
    private String consumerSecret;

    @SerializedName("text")
    private String text;

    public SendShortMessageModel() {
        //Required for GSON
    }

    //region Getters and Setters

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    //endregion

}
