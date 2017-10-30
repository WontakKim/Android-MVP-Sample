package com.wontak.sample.network.models;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;

    @SerializedName("html_url")
    public String htmlUrl;
}
