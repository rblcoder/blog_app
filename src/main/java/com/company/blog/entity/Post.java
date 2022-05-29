package com.company.blog.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    private Tag tag;

}
