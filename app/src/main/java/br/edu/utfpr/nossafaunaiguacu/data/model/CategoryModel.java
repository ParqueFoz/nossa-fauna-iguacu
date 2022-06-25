package br.edu.utfpr.nossafaunaiguacu.data.model;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {

    @SerializedName("id_categoria")
    private Integer id;
    @SerializedName("titulo")
    private String name;
    @SerializedName("url_img")
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
