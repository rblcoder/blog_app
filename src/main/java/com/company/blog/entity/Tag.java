package com.company.blog.entity;

import lombok.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;

@RepositoryRestResource
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String name;
}
