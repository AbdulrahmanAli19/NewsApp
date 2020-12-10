
package com.example.newsapp.pojo.modules;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Source {

    @SerializedName("category")
    private String mCategory;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private String mId;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("name")
    private String mName;
    @SerializedName("url")
    private String mUrl;

    public String getCategory() {
        return mCategory;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getId() {
        return mId;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getName() {
        return mName;
    }

    public String getUrl() {
        return mUrl;
    }

}
