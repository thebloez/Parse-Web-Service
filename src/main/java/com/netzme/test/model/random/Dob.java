package com.netzme.test.model.random;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dob {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("age")
    @Expose
    private Integer age;
}
