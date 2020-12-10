
package com.example.newsapp.pojo.modules;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Source2 {

    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
