package com.ishanvohra.halanxtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HouseResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("furnish_type")
    @Expose
    private String furnishType;
    @SerializedName("house_size")
    @Expose
    private Object houseSize;
    @SerializedName("cover_pic_url")
    @Expose
    private String coverPicUrl;
    @SerializedName("rent_from")
    @Expose
    private Double rentFrom;
    @SerializedName("security_deposit_from")
    @Expose
    private String securityDepositFrom;
    @SerializedName("available_flat_count")
    @Expose
    private Integer availableFlatCount;
    @SerializedName("available_room_count")
    @Expose
    private Integer availableRoomCount;
    @SerializedName("available_bed_count")
    @Expose
    private Integer availableBedCount;
    @SerializedName("accomodation_allowed_str")
    @Expose
    private String accomodationAllowedStr;
    @SerializedName("house_type")
    @Expose
    private Object houseType;
    @SerializedName("available_from")
    @Expose
    private Object availableFrom;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("display_title")
    @Expose
    private String displayTitle;
    @SerializedName("availability")
    @Expose
    private List<String> availability = null;
    @SerializedName("average_rating")
    @Expose
    private Object averageRating;
    @SerializedName("bhk_count")
    @Expose
    private Integer bhkCount;
    @SerializedName("visible")
    @Expose
    private Boolean visible;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFurnishType() {
        return furnishType;
    }

    public void setFurnishType(String furnishType) {
        this.furnishType = furnishType;
    }

    public Object getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Object houseSize) {
        this.houseSize = houseSize;
    }

    public String getCoverPicUrl() {
        return coverPicUrl;
    }

    public void setCoverPicUrl(String coverPicUrl) {
        this.coverPicUrl = coverPicUrl;
    }

    public Double getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(Double rentFrom) {
        this.rentFrom = rentFrom;
    }

    public String getSecurityDepositFrom() {
        return securityDepositFrom;
    }

    public void setSecurityDepositFrom(String securityDepositFrom) {
        this.securityDepositFrom = securityDepositFrom;
    }

    public Integer getAvailableFlatCount() {
        return availableFlatCount;
    }

    public void setAvailableFlatCount(Integer availableFlatCount) {
        this.availableFlatCount = availableFlatCount;
    }

    public Integer getAvailableRoomCount() {
        return availableRoomCount;
    }

    public void setAvailableRoomCount(Integer availableRoomCount) {
        this.availableRoomCount = availableRoomCount;
    }

    public Integer getAvailableBedCount() {
        return availableBedCount;
    }

    public void setAvailableBedCount(Integer availableBedCount) {
        this.availableBedCount = availableBedCount;
    }

    public String getAccomodationAllowedStr() {
        return accomodationAllowedStr;
    }

    public void setAccomodationAllowedStr(String accomodationAllowedStr) {
        this.accomodationAllowedStr = accomodationAllowedStr;
    }

    public Object getHouseType() {
        return houseType;
    }

    public void setHouseType(Object houseType) {
        this.houseType = houseType;
    }

    public Object getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Object availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public List<String> getAvailability() {
        return availability;
    }

    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }

    public Object getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Object averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getBhkCount() {
        return bhkCount;
    }

    public void setBhkCount(Integer bhkCount) {
        this.bhkCount = bhkCount;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
