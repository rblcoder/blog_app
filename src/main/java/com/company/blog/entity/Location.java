package com.company.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;


@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String city;

    private String state;

    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(city, location.city)
                && Objects.equals(state, location.state)
                && Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state, country);
    }
}
