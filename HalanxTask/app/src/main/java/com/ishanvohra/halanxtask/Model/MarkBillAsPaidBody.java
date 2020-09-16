package com.ishanvohra.halanxtask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarkBillAsPaidBody {
    @SerializedName("paid")
    @Expose
    private Boolean paid;

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
