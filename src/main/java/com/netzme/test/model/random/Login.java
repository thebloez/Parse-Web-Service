package com.netzme.test.model.random;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("md5")
    @Expose
    private String md5;
    @SerializedName("sha1")
    @Expose
    private String sha1;
    @SerializedName("sha256")
    @Expose
    private String sha256;
}
