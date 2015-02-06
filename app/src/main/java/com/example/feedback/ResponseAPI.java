package com.example.feedback;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by trmehta on 1/28/2015.
 */
public interface ResponseAPI {
    @GET("/sample_file.json") //get url after the root
    public void getFeed(Callback<FeedbackApiResponse> response);

    @POST("/sample_file.json")
    void addFeed(@Body FeedbackObject user, Callback<JSONObject> cb);
}
