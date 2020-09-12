package com.ishanvohra.halanxtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateBillBody {

    @SerializedName("house")
    @Expose
    private Integer house;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("tenant")
    @Expose
    private Integer tenant;

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getTenant() {
        return tenant;
    }

    public void setTenant(Integer tenant) {
        this.tenant = tenant;
    }

}
