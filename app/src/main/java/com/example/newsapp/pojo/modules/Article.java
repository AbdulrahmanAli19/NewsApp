
package com.example.newsapp.pojo.modules;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Article {

    @SerializedName("author")
    private String mAuthor;
    @SerializedName("content")
    private String mContent;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("publishedAt")
    private String mPublishedAt;
    @SerializedName("source")
    private Source2 mSource2;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("urlToImage")
    private String mUrlToImage;

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public Source2 getSource() {
        return mSource2;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getUrlToImage() {
        return mUrlToImage;
    }

}
