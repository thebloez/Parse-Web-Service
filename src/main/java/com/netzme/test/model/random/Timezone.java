package com.netzme.test.model.random;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timezone {
    @SerializedName("offset")
    @Expose
    private String offset;
    @SerializedName("description")
    @Expose
    private String description;
}
