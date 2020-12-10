package com.example.newsapp.pojo.modules;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class JournalDetails {

    @SerializedName("articles")
    private List<Article> mArticles;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("totalResults")
    private Long mTotalResults;

    public List<com.example.newsapp.pojo.modules.Article> getArticles() {
        return mArticles;
    }

    public String getStatus() {
        return mStatus;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

}
