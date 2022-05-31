package com.company.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @NonNull
    @JsonProperty("tag")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NonNull
    @JsonProperty("author")
    private Author author;

}
