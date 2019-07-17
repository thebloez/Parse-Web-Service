package com.netzme.test.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class RandomUser {

    private String gender, fullname, address, picture;

    public RandomUser(String gender, String fullname, String address, String picture) {
        this.gender = gender;
        this.fullname = fullname;
        this.address = address;
        this.picture = picture;
    }
}
