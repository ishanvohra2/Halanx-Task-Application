package com.ishanvohra.halanxtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InhouseAmenity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("amenity")
    @Expose
    private Amenity amenity;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("house")
    @Expose
    private Integer house;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Amenity getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenity amenity) {
        this.amenity = amenity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }


}
