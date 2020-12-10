package com.example.newsapp.pojo.data.retrofit;

import com.example.newsapp.pojo.modules.JournalDetails;
import com.example.newsapp.pojo.modules.Journals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("sources")
    Call<Journals> getAllNews(@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<JournalDetails> getTopHeadLines(@Query("sources") String journalId,
                                         @Query("apiKey") String apiKey);
}
