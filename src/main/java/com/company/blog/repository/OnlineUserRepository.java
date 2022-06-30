package com.company.blog.repository;

import com.company.blog.entity.OnlineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface OnlineUserRepository extends JpaRepository<OnlineUser, Long> {
}