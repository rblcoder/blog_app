package com.company.blog.repository;

import com.company.blog.entity.OnlineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface OnlineUserRepository extends JpaRepository<OnlineUser, Long> {
    List<OnlineUser> findByLocationCountry(String country);

    @Query("from OnlineUser u left join u.posts p where p.title like :title")
    List<OnlineUser> findUsersWithPostTitleLike(String title);
}