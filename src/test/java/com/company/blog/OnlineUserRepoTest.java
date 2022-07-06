package com.company.blog;

import com.company.blog.entity.Location;
import com.company.blog.entity.OnlineUser;
import com.company.blog.entity.Post;
import com.company.blog.entity.Tag;
import com.company.blog.repository.OnlineUserRepository;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
public class OnlineUserRepoTest {

    @Autowired
    OnlineUserRepository onlineUserRepository;

    @Test
    void testfindByLocationCountry() {
        onlineUserRepository
                .findByLocationCountry("India").get(0).getName().equals("Jack");
    }

    @Test
    void testfindUsersWithPostTitleLike() {
        onlineUserRepository
                .findUsersWithPostTitleLike("%Java%")
                .get(0).getName().equals("Pramita");
    }

    @Test
    void testfindUserById() {
        Location location = new Location("Bengaluru", "Karnataka", "India");
        OnlineUser onlineUserJack = new OnlineUser("Jack", "jack@rmail.com", "jack123");
        onlineUserJack.setLocation(location);
        onlineUserJack.setId(1L);

        OnlineUser userResult = onlineUserRepository.findById(1L).orElse(null);
        Assertions.assertEquals(onlineUserJack,userResult);

        Assertions.assertEquals(2, userResult.getPosts().size());
    }

    @Test
    void testfindUserByIdThenUpdate() {
        Location location = new Location("Bengaluru", "Karnataka", "India");
        OnlineUser onlineUserJack = new OnlineUser("Jack", "jack@rmail.com", "jack123");
        onlineUserJack.setLocation(location);
        onlineUserJack.setId(1L);

        OnlineUser userResult = onlineUserRepository.findById(1L).orElse(null);
        userResult.setName("Prasad");
        Assertions.assertTrue(Objects.equals("Prasad", onlineUserRepository.save(userResult).getName()));

    }
}
