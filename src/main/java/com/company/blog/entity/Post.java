package com.company.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "post_tags", joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @NonNull
    @JsonProperty("tag")
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NonNull
    @JsonProperty("author")
    private OnlineUser onlineUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id)
                && title.equals(post.title)
                && text.equals(post.text) && tags.equals(post.tags)
                && onlineUser.equals(post.onlineUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, tags, onlineUser);
    }
}
