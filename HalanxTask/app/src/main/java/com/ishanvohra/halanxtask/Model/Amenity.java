package com.ishanvohra.halanxtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amenity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("svg")
    @Expose
    private Object svg;
    @SerializedName("is_major")
    @Expose
    private Boolean isMajor;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getSvg() {
        return svg;
    }

    public void setSvg(Object svg) {
        this.svg = svg;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }


}
