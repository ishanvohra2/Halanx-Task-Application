package com.ishanvohra.halanxtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTenantResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("permanent_address")
    @Expose
    private PermanentAddress permanentAddress;
    @SerializedName("current_stay")
    @Expose
    private String currentStay;
    @SerializedName("parent_name")
    @Expose
    private Object parentName;
    @SerializedName("parent_phone_no")
    @Expose
    private Object parentPhoneNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PermanentAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(PermanentAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCurrentStay() {
        return currentStay;
    }

    public void setCurrentStay(String currentStay) {
        this.currentStay = currentStay;
    }

    public Object getParentName() {
        return parentName;
    }

    public void setParentName(Object parentName) {
        this.parentName = parentName;
    }

    public Object getParentPhoneNo() {
        return parentPhoneNo;
    }

    public void setParentPhoneNo(Object parentPhoneNo) {
        this.parentPhoneNo = parentPhoneNo;
    }

}
