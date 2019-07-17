package com.netzme.test.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "configuration")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Config {

    @Id
    private UUID id;

    private String url;

    private String requestTemplate;

    private String responseTemplate;

    public Config(UUID id, String url, String requestTemplate, String responseTemplate) {
        this.id = id;
        this.url = url;
        this.requestTemplate = requestTemplate;
        this.responseTemplate = responseTemplate;
    }
}
