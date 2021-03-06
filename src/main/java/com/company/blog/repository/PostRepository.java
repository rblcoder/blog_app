package com.company.blog.repository;

import com.company.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p.* from post p inner join online_user u on p.author_id = u.id and u.name=:name",
            nativeQuery = true)
    List<Post> findAllPostsByOnlineUserName(@Param("name") String name);

    List<Post> findByTitle(String title);

    List<Post> findByTitleLike(String title);

    List<Post> findByTitleContains(String title);
}