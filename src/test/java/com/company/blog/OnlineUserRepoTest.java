package com.company.blog;

import com.company.blog.repository.OnlineUserRepository;
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
}
