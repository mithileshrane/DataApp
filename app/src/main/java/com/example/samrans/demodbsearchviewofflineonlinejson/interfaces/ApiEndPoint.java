package com.example.samrans.demodbsearchviewofflineonlinejson.interfaces;

import com.example.samrans.demodbsearchviewofflineonlinejson.models.Movies;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Samrans on 13-12-2017.
 */

public interface ApiEndPoint {
    @FormUrlEncoded
    @POST("upcoming")
    Call<Movies> getCategoryMenu(@Field("api_key") String userToken);
}
