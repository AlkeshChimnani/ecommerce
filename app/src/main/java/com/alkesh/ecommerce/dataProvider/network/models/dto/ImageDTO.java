package com.alkesh.ecommerce.dataProvider.network.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImageDTO {
    @SerializedName("url")
    @Expose
    private String url = null;

    @SerializedName("width")
    @Expose
    private Integer width = null;

    @SerializedName("height")
    @Expose
    private Integer height = null;

    @SerializedName("format")
    @Expose
    private String format = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}