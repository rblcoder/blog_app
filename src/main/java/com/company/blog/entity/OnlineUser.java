package com.company.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@RepositoryRestResource
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "online_user")
public class OnlineUser {
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


    @OneToMany(mappedBy = "onlineUser")
    List<Post> posts;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, max = 16)
    @NotEmpty(message = "Password name must not be empty")
    @NonNull
    private String password;
}