package com.company.blog;

import com.company.blog.entity.Location;
import com.company.blog.entity.OnlineUser;
import com.company.blog.repository.OnlineUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

        Assertions.assertEquals(onlineUserJack,
                onlineUserRepository.findById(1L).orElse(null));

    }
}
