package com.example.frost.exercise1;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by frost on 19.11.16.
 */

public interface ShortMessageProvider {
    //String ENDPOINT = "https://simple-web-api.azurewebsites.net/";

    @GET("/api/ShortMessages/{consumerKey}")
    Call<ShortMessages> getShortMessages(@Path("consumerKey") String consumerKey);

    @POST("/api/ShortMessages")
    Call<ShortMessageModel> sendShortMessage(@Body SendShortMessageModel shortMessage); /*{
        return null;
    }*/
}
