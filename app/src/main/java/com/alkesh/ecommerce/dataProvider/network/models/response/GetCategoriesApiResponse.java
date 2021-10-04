package com.alkesh.ecommerce.dataProvider.network.models.response;

import com.alkesh.ecommerce.dataProvider.network.models.dto.CategoryDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetCategoriesApiResponse extends BaseApiResponse {
    @SerializedName("content")
    @Expose
    private ArrayList<CategoryDTO> data;

    public ArrayList<CategoryDTO> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryDTO> data) {
        this.data = data;
    }
}