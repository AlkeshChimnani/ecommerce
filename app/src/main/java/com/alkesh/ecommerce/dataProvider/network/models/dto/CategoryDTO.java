package com.alkesh.ecommerce.dataProvider.network.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    @SerializedName("type")
    @Expose
    private String type = null;

    @SerializedName("cols")
    @Expose
    private Integer cols = null;

    @SerializedName("images")
    @Expose
    private ArrayList<ImageDTO> listImages = null;


    @SerializedName("show")
    @Expose
    private Integer show = null;

    @SerializedName("title")
    @Expose
    private String title = null;

    @SerializedName("height")
    @Expose
    private Integer height = null;

    @SerializedName("url")
    @Expose
    private String url = null;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public ArrayList<ImageDTO> getListImages() {
        return listImages;
    }

    public void setListImages(ArrayList<ImageDTO> listImages) {
        this.listImages = listImages;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}