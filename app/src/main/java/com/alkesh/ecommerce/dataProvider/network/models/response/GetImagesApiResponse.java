package com.alkesh.ecommerce.dataProvider.network.models.response;

import com.alkesh.ecommerce.dataProvider.network.models.dto.CategoryDTO;
import com.alkesh.ecommerce.dataProvider.network.models.dto.ImageDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetImagesApiResponse extends BaseApiResponse {
    @SerializedName("images")
    @Expose
    private ArrayList<ImageDTO> data;

    public ArrayList<ImageDTO> getData() {
        return data;
    }

    public void setData(ArrayList<ImageDTO> data) {
        this.data = data;
    }
}