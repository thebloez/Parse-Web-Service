package com.netzme.test.model.random;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RandomResponse {
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("info")
    @Expose
    private Info info;
}
