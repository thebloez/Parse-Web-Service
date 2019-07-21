package com.netzme.test.model.random;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Id {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private Object value;
}
