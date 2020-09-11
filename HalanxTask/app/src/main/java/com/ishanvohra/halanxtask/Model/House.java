package com.ishanvohra.halanxtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class House {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("display_title")
    @Expose
    private String displayTitle;
    @SerializedName("inhouse_amenities")
    @Expose
    private List<InhouseAmenity> inhouseAmenities = null;
    @SerializedName("society_amenities")
    @Expose
    private List<Object> societyAmenities = null;
    @SerializedName("agreement_commencement_date")
    @Expose
    private String agreementCommencementDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover_pic_url")
    @Expose
    private String coverPicUrl;
    @SerializedName("preferred_food")
    @Expose
    private Object preferredFood;
    @SerializedName("available_from")
    @Expose
    private String availableFrom;
    @SerializedName("available")
    @Expose
    private Boolean available;
    @SerializedName("visible")
    @Expose
    private Boolean visible;
    @SerializedName("bhk_count")
    @Expose
    private Integer bhkCount;
    @SerializedName("house_type")
    @Expose
    private String houseType;
    @SerializedName("furnish_type")
    @Expose
    private String furnishType;
    @SerializedName("available_accomodation_types")
    @Expose
    private List<String> availableAccomodationTypes = null;
    @SerializedName("accomodation_allowed")
    @Expose
    private List<String> accomodationAllowed = null;
    @SerializedName("house_size")
    @Expose
    private String houseSize;
    @SerializedName("two_wheeler_parking_available")
    @Expose
    private Boolean twoWheelerParkingAvailable;
    @SerializedName("four_wheeler_parking_available")
    @Expose
    private Boolean fourWheelerParkingAvailable;
    @SerializedName("application")
    @Expose
    private Object application;
    @SerializedName("managed_by")
    @Expose
    private Integer managedBy;
    @SerializedName("area_managers")
    @Expose
    private List<Integer> areaManagers = null;
    @SerializedName("rent_included_expenses")
    @Expose
    private List<Object> rentIncludedExpenses = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public List<InhouseAmenity> getInhouseAmenities() {
        return inhouseAmenities;
    }

    public void setInhouseAmenities(List<InhouseAmenity> inhouseAmenities) {
        this.inhouseAmenities = inhouseAmenities;
    }

    public List<Object> getSocietyAmenities() {
        return societyAmenities;
    }

    public void setSocietyAmenities(List<Object> societyAmenities) {
        this.societyAmenities = societyAmenities;
    }

    public String getAgreementCommencementDate() {
        return agreementCommencementDate;
    }

    public void setAgreementCommencementDate(String agreementCommencementDate) {
        this.agreementCommencementDate = agreementCommencementDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverPicUrl() {
        return coverPicUrl;
    }

    public void setCoverPicUrl(String coverPicUrl) {
        this.coverPicUrl = coverPicUrl;
    }

    public Object getPreferredFood() {
        return preferredFood;
    }

    public void setPreferredFood(Object preferredFood) {
        this.preferredFood = preferredFood;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getBhkCount() {
        return bhkCount;
    }

    public void setBhkCount(Integer bhkCount) {
        this.bhkCount = bhkCount;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getFurnishType() {
        return furnishType;
    }

    public void setFurnishType(String furnishType) {
        this.furnishType = furnishType;
    }

    public List<String> getAvailableAccomodationTypes() {
        return availableAccomodationTypes;
    }

    public void setAvailableAccomodationTypes(List<String> availableAccomodationTypes) {
        this.availableAccomodationTypes = availableAccomodationTypes;
    }

    public List<String> getAccomodationAllowed() {
        return accomodationAllowed;
    }

    public void setAccomodationAllowed(List<String> accomodationAllowed) {
        this.accomodationAllowed = accomodationAllowed;
    }

    public String getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(String houseSize) {
        this.houseSize = houseSize;
    }

    public Boolean getTwoWheelerParkingAvailable() {
        return twoWheelerParkingAvailable;
    }

    public void setTwoWheelerParkingAvailable(Boolean twoWheelerParkingAvailable) {
        this.twoWheelerParkingAvailable = twoWheelerParkingAvailable;
    }

    public Boolean getFourWheelerParkingAvailable() {
        return fourWheelerParkingAvailable;
    }

    public void setFourWheelerParkingAvailable(Boolean fourWheelerParkingAvailable) {
        this.fourWheelerParkingAvailable = fourWheelerParkingAvailable;
    }

    public Object getApplication() {
        return application;
    }

    public void setApplication(Object application) {
        this.application = application;
    }

    public Integer getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(Integer managedBy) {
        this.managedBy = managedBy;
    }

    public List<Integer> getAreaManagers() {
        return areaManagers;
    }

    public void setAreaManagers(List<Integer> areaManagers) {
        this.areaManagers = areaManagers;
    }

    public List<Object> getRentIncludedExpenses() {
        return rentIncludedExpenses;
    }

    public void setRentIncludedExpenses(List<Object> rentIncludedExpenses) {
        this.rentIncludedExpenses = rentIncludedExpenses;
    }

}
