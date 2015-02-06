package com.example.feedback;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by trmehta on 1/27/2015.
 */
public interface FeedbackAPI {
    @GET("/sample_file.json") //get url after the root
    public void getFeed(Callback<List<FeedbackObject>> response);
}
