package com.company.blog.entity;

import lombok.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;


@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String city;

    private String state;

    private String country;
}
