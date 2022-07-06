package com.company.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RepositoryRestResource
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "online_user")
public class OnlineUser {
    @OneToMany(mappedBy = "onlineUser", fetch = FetchType.LAZY)
    List<Post> posts;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Embedded
    private Location location;
    @NotEmpty(message = "User name must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @Size(min = 4, max = 15)
    @NonNull
    private String name;
    @NonNull
    @Email
    private String emailId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, max = 16)
    @NotEmpty(message = "Password name must not be empty")
    @NonNull
    private String password;

    public void addPost(Post post) {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnlineUser that = (OnlineUser) o;
        return Objects.equals(id, that.id)
                && Objects.equals(location, that.location)
                && name.equals(that.name) && emailId.equals(that.emailId)
                && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posts, id, location, name, emailId, password);
    }
}