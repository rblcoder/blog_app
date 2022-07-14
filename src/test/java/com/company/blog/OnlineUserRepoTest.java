package com.company.blog;

import com.company.blog.entity.Location;
import com.company.blog.entity.OnlineUser;
import com.company.blog.repository.OnlineUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@DataJpaTest
public class OnlineUserRepoTest {

    @Autowired
    OnlineUserRepository onlineUserRepository;

    @Test
    void testfindByLocationCountry() {
        Assertions.assertEquals("Jack", onlineUserRepository
                .findByLocationCountry("India").get(0).getName());
    }

    @Test
    void testfindUsersWithPostTitleLike() {
        Assertions.assertEquals("Pramita", onlineUserRepository
                .findUsersWithPostTitleLike("%Java%")
                .get(0).getName());
    }

    @Test
    void testfindUserById() {
        Location location = new Location("Bengaluru", "Karnataka", "India");
        OnlineUser onlineUserJack = new OnlineUser("Jack", "jack@rmail.com", "jack123");
        onlineUserJack.setLocation(location);
        onlineUserJack.setId(1L);

        OnlineUser userResult = onlineUserRepository.findById(1L).orElse(null);
        Assertions.assertEquals(onlineUserJack, userResult);

        Assertions.assertEquals(2, Objects.requireNonNull(userResult).getPosts().size());
    }

    @Test
    void testfindUserByIdThenUpdate() {
        Location location = new Location("Bengaluru", "Karnataka", "India");
        OnlineUser onlineUserJack = new OnlineUser("Jack", "jack@rmail.com", "jack123");
        onlineUserJack.setLocation(location);
        onlineUserJack.setId(1L);

        OnlineUser userResult = onlineUserRepository.findById(1L).orElse(null);
        userResult.setName("Prasad");
        Assertions.assertEquals("Prasad",
                onlineUserRepository.save(userResult).getName());

    }

    @Test
    void testSaveUserExceptionWhenPasswordLengthLessThan6() {
        Location location = new Location("Bengaluru", "Karnataka", "India");
        OnlineUser onlineUserJack = new OnlineUser("Jack", "jack@rmail.com", "j");
        onlineUserJack.setLocation(location);

        Assertions.assertThrows(ConstraintViolationException.class,
                ()-> onlineUserRepository.save(onlineUserJack));

    }

}
